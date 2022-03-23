
/**********COMANDO 1 **********/
//NORMAL
docker run -d -P 80:80 \
-v /var/run/docker.sock:/tmp/docker.sock:ro \
-v /root/docker/ssl/pablokbs.com/:/etc/nginx/certs/ \
jwilder/nginx-proxy

//DOCKER-COMPOSE -> docker-compose.yml
nginx:
 image: jwilder/nginx-proxy
 ports:
  - "80:80"
  - "443:443"
 volumes:
  - /var/run/docker.sock:/tmp/docker.sock:ro
  - /root/docker/ssl/pablokbs.com/:/etc/nginx/certs/


/**********COMANDO 2 **********/
//NORMAL
docker run -d -p 80:80 \
-e VIRTUAL_HOST=www.pablokbs.com,pablokbs.com \
-e DEBUG=false \
-e ENVIRONMENT=production \
-v /root/docker/web/mycode/:/var/www/pablokbs_code/ \
pablokbs/myweb

//DOCKER-COMPOSE -> docker-compose.yml
myweb:
 image: pablokbs/myweb
 expose:
  - "80"
 environment:
  - VIRTUAL_HOST=www.pablokbs.com,pablokbs.com
  - DEBUG=false
  - ENVIRONMENT=production


/************* COMANDOS *************/ 
kubectl version --client=true
kubectl config get-contexts

//NODOS
kubectl get nodes

//NAMESPACES
kubectl get ns 

//PODS
kubectl -n kube-system get pods
kubectl -n kube-system get pods -o wide
kubectl -n kube-system delete pod  kube-proxy-w452d 