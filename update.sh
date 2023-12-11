sudo systemctl stop devops

git pull

rm target/*

mvn clean install

sudo systemctl start devops
