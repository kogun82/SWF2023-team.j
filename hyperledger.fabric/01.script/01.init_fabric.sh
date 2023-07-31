#docker setting
conda remove --name hyperledger --all
conda create -n hyperledger python=3.10 pip
conda activate hyperledger

#nvm setting
brew install nvm
mkdir ~/.nvm
vi ~/.zshrc
export NVM_DIR="$HOME/.nvm"
[ -s "/usr/local/opt/nvm/nvm.sh" ] && . "/usr/local/opt/nvm/nvm.sh"                                       # This loads nvm
[ -s "/usr/local/opt/nvm/etc/bash_completion.d/nvm" ] && . "/usr/local/opt/nvm/etc/bash_completion.d/nvm" # This loads nvm bash_completion

nvm list
nvm install v12.22.12
nvm use v12.22.12

#docker initialization
# !!! THIS WILL REMOVE ALL YOUR DOCKER CONTAINERS AND IMAGES !!!
# remove all containers
$ docker rm $(docker ps -qa)
# remove all mages
$ docker rmi --force $(docker images -qa)
# prune networks
$ docker network prune

#hyperledger
base_dir = /Users/kogun82/Documents/workspace/SWF2023-team.j/hyperledger.fabric/02.fabric

cd $base_dir
curl -sSL https://bit.ly/2ysbOFE | bash -s -- 2.2.0 1.4.7

#env setting
export FABRIC_HOME=/Users/kogun82/Documents/workspace/SWF2023-team.j/hyperledger.fabric/02.fabric/fabric-samples
export FABRIC_CFG_PATH=/Users/kogun82/Documents/workspace/SWF2023-team.j/hyperledger.fabric/02.fabric/fabric-samples/config
export GO_HOME=/Users/kogun82/Documents/program/go/current
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home
export PATH=$GO_HOME/bin:$JAVA_HOME/bin:$PATH

cd fabric-samples/test-network

./network.sh down
./network.sh up createChannel -ca -c mychannel -s couchdb

# ./network.sh deployCC -ccn basic -ccp ../asset-transfer-basic/chaincode-go -ccl go

./network.sh deployCC -ccn geno -ccv 1 -cci initLedger -ccl go -ccp ../chaincode/fabcar/go/

# fabric-samples env setting
export PATH=~${PWD}/bin:$PATH
# export FABRIC_CFG_PATH=/Users/kogun82/Documents/workspace/SWF2023-team.j/hyperledger.fabric/02.fabric/fabric-samples/config
export FABRIC_CFG_PATH=/Users/kogun82/Documents/workspace/SWF2023-team.j/hyperledger.fabric/02.fabric/geno/config
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/test-network/organizations/peerOrganizations/org1.example.com/peers/peer0.org1. example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/test-network/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example. com/msp
export CORE_PEER_ADDRESS=localhost:7051
