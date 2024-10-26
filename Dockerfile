# Etapa 1: Construir o JAR
FROM openjdk:21-jdk-slim AS build
WORKDIR /app

# Instalar curl, unzip e zip
RUN apt-get update && apt-get install -y curl unzip zip \
    && curl -s "https://get.sdkman.io" | bash \
    && bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install gradle 8.2"

# Copiar arquivos do projeto
COPY build.gradle settings.gradle ./
COPY src ./src

# Executar o build
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && gradle clean build -x test"

# Etapa 2: Criar a imagem do aplicativo
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
