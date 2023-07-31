# 1. 블록체인 네트워크 구성
# → 명령어 실행 위치 : $FABRIC_HOME/first-network
# 이전 데몬 삭제 명령어
# ./byfn.sh down

docker rm -f $(docker ps -aq)
docker rmi -f $(docker images | grep fabcar | awk '{print $3}')

# 2. Launch network
# → 명령어 실행 위치 : $FABRIC_HOME/fabcar
#spin up a blockchain network comprising peers, orderers, certificate authorities and more
#install and instantiate a JavaScript version of the FabCar smart contract
cd $FABRIC_HOME/fabcar
./networkDown.sh
./startFabric.sh javascript

# 3. Install the application
cd javascript
npm install

# enrollAdmin.js: Admin 등록
# invoke.js: 원장 업데이트
# /node_modules: npm에 의해 설치된 모듈 및 패키지
# package.json: fabcar 어플리케이션 정보
# query.js: 원장 쿼리
# registerUser.js: User 등록
# wallet: admin과 user의 Credentials
# HLF 네트워크에 Admin과 User를 등록한다. 명령어 실행 후 /wallet에 admin과 user1의 개인키, 공개키, 인증서가 저장된다.

node enrollAdmin.js
node registerUser.js
node invoke.js
