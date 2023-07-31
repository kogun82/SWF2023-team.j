/*
SPDX-License-Identifier: Apache-2.0
*/

package main

import (
	"encoding/json"
	"fmt"
	"strconv"

	"github.com/hyperledger/fabric-contract-api-go/contractapi"
)

// SmartContract provides functions for managing a car
type SmartContract struct {
	contractapi.Contract
}

// Car describes basic details of what makes up a car
type Gene struct {
	Name   string `json:"name"`
	Age	   string `json:"age"`
	Add    string `json:"add"`
	Chr    string `json:"chr"`
}

// QueryResult structure used for handling result of query
type QueryResult struct {
	Key    string `json:"Key"`
	Record *Gene
}

// InitLedger adds a base set of cars to the ledger
func (s *SmartContract) InitLedger(ctx contractapi.TransactionContextInterface) error {
	genes := []Gene{
		Gene{Name: "team.j", Age: "18", Add: "deajeon", Chr: "ch1"},
	}

	for i, gene := range genes {
		geneAsBytes, _ := json.Marshal(gene)
		err := ctx.GetStub().PutState("GENE"+strconv.Itoa(i), geneAsBytes)

		if err != nil {
			return fmt.Errorf("Failed to put to world state. %s", err.Error())
		}
	}

	return nil
}

// CreateCar adds a new car to the world state with given details
func (s *SmartContract) CreateGene(ctx contractapi.TransactionContextInterface, geneNumber string, name string, age string, add string, chr string) error {
	gene := Gene{
		Name: name,
		Age: age,
		Add: add,
		Chr: chr,
	}

	geneAsBytes, _ := json.Marshal(gene)

	return ctx.GetStub().PutState(geneNumber, geneAsBytes)
}

// QueryCar returns the car stored in the world state with given id
func (s *SmartContract) QueryCar(ctx contractapi.TransactionContextInterface, geneNumber string) (*Gene, error) {
	geneAsBytes, err := ctx.GetStub().GetState(geneNumber)

	if err != nil {
		return nil, fmt.Errorf("Failed to read from world state. %s", err.Error())
	}

	if geneAsBytes == nil {
		return nil, fmt.Errorf("%s does not exist", geneNumber)
	}

	gene := new(Gene)
	_ = json.Unmarshal(geneAsBytes, gene)

	return gene, nil
}

// QueryAllCars returns all cars found in world state
func (s *SmartContract) QueryAllGene(ctx contractapi.TransactionContextInterface) ([]QueryResult, error) {
	startKey := ""
	endKey := ""

	resultsIterator, err := ctx.GetStub().GetStateByRange(startKey, endKey)

	if err != nil {
		return nil, err
	}
	defer resultsIterator.Close()

	results := []QueryResult{}

	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next()

		if err != nil {
			return nil, err
		}

		gene := new(Gene)
		_ = json.Unmarshal(queryResponse.Value, gene)

		queryResult := QueryResult{Key: queryResponse.Key, Record: gene}
		results = append(results, queryResult)
	}

	return results, nil
}

// ChangeCarOwner updates the owner field of car with given id in world state
func (s *SmartContract) ChangeChr(ctx contractapi.TransactionContextInterface, geneNumber string, newChr string) error {
	gene, err := s.QueryCar(ctx, geneNumber)

	if err != nil {
		return err
	}

	gene.Chr = newChr

	geneAsBytes, _ := json.Marshal(gene)

	return ctx.GetStub().PutState(geneNumber, geneAsBytes)
}

func main() {

	chaincode, err := contractapi.NewChaincode(new(SmartContract))

	if err != nil {
		fmt.Printf("Error create fabcar chaincode: %s", err.Error())
		return
	}

	if err := chaincode.Start(); err != nil {
		fmt.Printf("Error starting fabcar chaincode: %s", err.Error())
	}
}
