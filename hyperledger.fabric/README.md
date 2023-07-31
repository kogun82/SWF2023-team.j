###anaconda install

```
conda remove --name hyperledger --all
conda create -n hyperledger python==2.7.13 pip
conda activate hyperledger
```

###nvm install

```
nvm list
nvm install v8.7
nvm use 8.7
```

###docker

```
sudo chmod 666 /var/run/docker.sock

docker run -d --name portainer -p 8000:8000 -p 9443:9443 -p 9090:9000 -v /var/run/docker.sock:/var/run/docker.sock -v /BiO/docker/portainer:/data --restart=always portainer/portainer-ce:latest

docker container start 4c90e25db6ea
docker build ./ -t compose
```

###nvm comoser-palygroud run

```
composer-playground
```

### jq 설치

```
sudo apt-get install jq -y
jq --version
```

###hyperledger fabric sample 실행

###### fabric download

```
curl -sSL https://bit.ly/2ysbOFE | bash -s -- 2.3.1 1.4.9
curl -sSL https://bit.ly/2ysbOFE | bash -s -- 2.2.0 1.4.7 \*
```

###### 기본 네트워크 실행

```
fabric-samples/test-network 이동
./network.sh down 이전 네트워크 삭제
./network.sh up 네트워크 실행

./network.sh createChannel -c ${channel_name}

###### CA 및 couchdb를 사용하는 네트워크 실행
./network.sh up createChannel -ca -c mychannel -s couchdb *
```

###### 채널을 생성하고 체인코드를 배포한다.

```
./network.sh deployCC -ccn basic -ccp ../asset-transfer-basic/chaincode-go -ccl go
```

###### 체인코드까지 배포되었으니 네트워크를 이용해볼 차례다. cli 컨테이너를 사용한다.

###### 경로와 설정을 잡아줘야 한다. 아래 명령어는 fabric-samples에서 실행해야한다.

```
export PATH=~${PWD}/bin:$PATH
export FABRIC_CFG_PATH=${PWD}/config/
export FABRIC_CFG_PATH=/home/kogun82/workspace/fabric-samples/config *
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/test-network/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/test-network/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
export CORE_PEER_ADDRESS=localhost:7051
```

###### 데이터 입력 실행

```
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls --cafile "${PWD}/test-network/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem" -C mychannel -n basic --peerAddresses localhost:7051 --tlsRootCertFiles "${PWD}/test-network/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt" --peerAddresses localhost:9051 --tlsRootCertFiles "${PWD}/test-network/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt" -c '{"function":"InitLedger","Args":[]}'

```

###### 데이터 확인 실행

```
peer chaincode query -C mychannel -n basic -c '{"Args":["GetAllAssets"]}'
```

###### 데이터 변경 실행

```
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls --cafile "${PWD}/test-network/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem" -C mychannel -n basic --peerAddresses localhost:7051 --tlsRootCertFiles "${PWD}/test-network/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt" --peerAddresses localhost:9051 --tlsRootCertFiles "${PWD}/test-network/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt" -c '{"function":"TransferAsset","Args":["asset6","A45hvn"]}'
```

###### 데이터 변경 확인

```
peer chaincode query -C mychannel -n basic -c '{"Args":["GetAllAssets"]}'
```

###hyperledger fabric sample 실행
