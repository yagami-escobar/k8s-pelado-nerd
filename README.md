#Curso K8s Pelado Nerd
# Curso de K8s Pelado Nerd

### Secrets: 
Los secrets son recursos muy similares a los Config Maps, excepto que estos su data esta codificada en Base 64.

#### 1ra Forma:
``
kubectl create secret generic db-credentials --from-literal=user=admin  --from-literal=password=1234
``

#### 2da Forma: Secret.yaml
```
apiVersion: v1
kind: Secret
metadata:
  name: db-credentials
type: Opaque
data:
  username: admin
  password: 1234
```
#### Pod - secretKeyRef
```
apiVersion: v1
kind: Pod
metadata:
  name: nginx
spec:
  containers:
  - name: nginx
    image: nginx:alpine
    env:
    - name: MI_VARIABLE
      value: "yagami"
    - name: MYSQL_USER
      valueFrom:
        secretKeyRef:
          name: db-credentials
          key: username
    - name: MYSQL_PASSWORD
      valueFrom:
        secretKeyRef:
          name: db-credentials
          key: password
    ports:
    - containerPort: 80
```
