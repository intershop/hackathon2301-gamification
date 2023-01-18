FROM node:14-alpine as builder

ARG USER
ARG EMAIL
ARG PASSWORD


ENV NPM_USER=$USER
ENV NPM_EMAIL=$EMAIL
ENV NPM_PASSWORD=$PASSWORD

RUN npm install -g @angular/cli

## install 
COPY .npmrc package.json /workspace/
WORKDIR /workspace
RUN npm install

## build 
COPY src/ /workspace/src/
COPY  angular.json extra-webpack.config.js tsconfig.json tsconfig.app.json tsconfig.spec.json  /workspace/
WORKDIR /workspace
RUN npm run build

### STAGE 2: nginx environment ###
FROM nginx:1.15.3-alpine

## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*

## From ‘builder’ stage copy over the artifacts in dist folder to default nginx public folder
COPY --from=builder /workspace/dist /usr/share/nginx/html
EXPOSE 80

HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 CMD [ "curl http://localhost:80" ]

ENTRYPOINT ["nginx", "-g", "daemon off;"]
