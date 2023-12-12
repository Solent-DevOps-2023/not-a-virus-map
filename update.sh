sudo systemctl stop devops

git pull

rm -rf target/*

mvn clean install

sudo systemctl start devops
