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

base_dir = /Users/kogun82/Documents/workspace/SWF2023-team.j/hyperledger.fabric/02.fabric

cd geno/test-network

./network.sh down
./network.sh up createChannel -ca -c mychannel -s couchdb
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

cd /Users/kogun82/Documents/workspace/SWF2023-team.j/hyperledger.fabric/02.fabric/geno
./test_run.sh

cd /Users/kogun82/Documents/workspace/SWF2023-team.j/hyperledger.fabric/02.fabric/geno/fabcar/javascript
npm install

node enrollAdmin.js
node registerUser.js
node invoke.js
