conda remove --name composer --all
conda create -n composer python=2.7.13 pip
conda activate composer

nvm install 8.7
nvm use 8.7
npm install npm@5.6.0 -g
node -v
npm -v

npm install -g composer-cli@0.20
npm install -g composer-rest-server@0.20
npm install -g generator-hyperledger-composer@0.20
npm install -g yo
npm install -g composer-playground

composer-playground
