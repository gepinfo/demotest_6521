FROM node:20.2.0-alpine as build
COPY . /app
WORKDIR /app
RUN npm install --legacy-peer-deps
RUN npm install -g @angular/cli@latest
RUN npm install -g serve
RUN npm install -g concurrently
RUN npm upgrade -g @angular/cli
RUN ng build  --output-hashing all

FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/* && rm -rf /etc/nginx/nginx.conf
COPY --from=build /app/dist/GeppettoBuilder /usr/share/nginx/html
COPY /nginx/nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]