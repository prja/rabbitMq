# rabbitMq
working  with  RabbitMq

# Run rabbitmq
docker run -d --restart always --name rabbitmq --hostname docker-rabbitmq -p 5672:5672 -p 15672:15672 -v d:/development/docker/data/rabbitmq:/var/lib/rabbitmq/mnesia rabbitmq:3.12-management

