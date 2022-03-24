
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
kubectl get nodes -o wide 

//NAMESPACES
kubectl get ns 

//PODS
kubectl -n kube-system get pods
kubectl -n kube-system get pods -o wide 
kubectl -n kube-system delete pod  kube-proxy-w452d -> 'Eliminar el pod x del namespace kube-system'
kubectl -n default get pods -> 'Ver los pods del namespace default'
kubectl apply -f 01-pod.yaml  -> 'Aplicar un Manifiesto yaml'
kubectl get pods nginx -> 'Ver el estado de Nuesto POD.'
kubectl get pods nginx -o yaml -> 'Nos muestra el yaml de nuestro POD, esta todo lo del manifiesto + variables por defecto'
kubectl describe pod xxxxx -> 'Describe un POD'

/************* MANIFIESTOS *************/ 
//01-pod.yaml -> Manifiesto Pod Simple
apiVersion: v1
 kind: Pod
metadata: 
 name: nginx
spec:
 containers:
 - name: nginx
   image: nginx:alpine

kubectl apply -f 01-pod.yaml -> Aplicar el Manifiesto, si no le especificamos el namespace lo va crear en el namespace default.
kubectl -n default get pods -> Obtener pods del ns default

//02-pod.yaml -> Manifiesto Complejo.
apiVersion: v1
kind: Pod
metadata:
 name: nginx
spec:
 containers:
 - name: nginx
   image: nginx:alpine
   env:
   - name: NOMBRE
     value: "jhordan"
   - name: APELLIDO
     value: "Escobar"
   - name: IP_AGENT_HOST
     valueFrom:
      fieldRef:
       fieldPath: status.hostIP
   resources:
    requests:
     memory: "64Mi"
     cpu: "200m"
    limits:
     memory: "128Mi"
     cpu: "500m"
   readinessProbe:
    httpGet:
     path: /
     port: 80
    initialDelaySeconds: 5
    periodSeconds: 10
   livenessProbe:
    tcpSocket:
     port: 80
    initialDelaySeconds: 15
    periodSeconds: 20
   ports:
   - containerPort: 80


    
  
