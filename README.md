# Summarify AI

A lightweight, provider-agnostic AI-powered text summarization service built with Spring Boot. Supports clean REST API, error handling, and future extensibility for multiple AI providers.

## 🚀 Features

- **AI-Powered Summarization**: Leverages OpenAI's models to generate concise summaries of text
- **RESTful API**: Clean and well-documented API endpoints
- **History Tracking**: Stores all summarization requests and responses
- **Swagger Documentation**: Interactive API documentation
- **Comprehensive Error Handling**: Robust exception handling with detailed error responses
- **Docker Support**: Easy deployment with Docker and Docker Compose
- **AOP Logging**: Aspect-oriented logging for monitoring and debugging

## 🛠️ Technologies

- **Java 24**: Latest Java version for optimal performance
- **Spring Boot 3.5.3**: Modern Spring Boot framework
- **Spring Data JPA**: For database operations
- **Spring AI**: Integration with AI models
- **PostgreSQL**: Relational database for storing summary history
- **Lombok**: Reduces boilerplate code
- **Swagger/OpenAPI**: API documentation
- **Docker & Docker Compose**: Containerization and orchestration
- **Spring AOP**: For cross-cutting concerns like logging

## 📋 API Endpoints

### Summarize Text

```
POST /api/summarize
```

**Request Body:**

```json
{
  "text": "Your long text to be summarized goes here..."
}
```

**Response:**

```json
{
  "summary": "The concise summary of your text."
}
```

**cURL Example:**

```bash
curl -X POST http://localhost:8080/api/summarize \
  -H "Content-Type: application/json" \
  -d '{"text":"Your long text to be summarized goes here..."}'
```

### Get Summary History by ID

```
GET /api/history/{id}
```

**Response:**

```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "inputText": "Original text that was summarized",
  "summaryText": "The generated summary",
  "createdAt": "2023-09-15 14:30:45",
  "updatedAt": "2023-09-15 14:30:45"
}
```

**cURL Example:**

```bash
curl -X GET http://localhost:8080/api/history/123e4567-e89b-12d3-a456-426614174000
```

### Get All Summary Histories

```
GET /api/history
```

**Response:**

```json
[
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "inputText": "Original text that was summarized",
    "summaryText": "The generated summary",
    "createdAt": "2023-09-15 14:30:45",
    "updatedAt": "2023-09-15 14:30:45"
  },
  {
    "id": "223e4567-e89b-12d3-a456-426614174001",
    "inputText": "Another text that was summarized",
    "summaryText": "Another generated summary",
    "createdAt": "2023-09-16 10:15:30",
    "updatedAt": "2023-09-16 10:15:30"
  }
]
```

**cURL Example:**

```bash
curl -X GET http://localhost:8080/api/history
```

## 🔧 Configuration

Key configuration properties in `application.properties`:

```properties
# Server Configuration
server.port=8080
spring.application.name=summarify

# Database Configuration
spring.datasource.url=jdbc:postgresql://postgres:5432/summarify
spring.datasource.username=username
spring.datasource.password=password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# OpenAI Configuration
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=openai/gpt-4o-mini

# Swagger Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## 🐳 Running with Docker

### Prerequisites

- Docker and Docker Compose installed
- OpenAI API key

### Steps

1. Clone the repository:

```bash
git clone https://github.com/yourusername/summarify-ai.git
cd summarify-ai
```

2. Set your OpenAI API key as an environment variable:

```bash
export OPENAI_API_KEY=your_openai_api_key_here
```

3. Build and start the containers:

```bash
docker-compose up -d
```

4. The application will be available at http://localhost:8080

5. Access the Swagger UI at http://localhost:8080/swagger-ui.html

## 🛠️ Building the Application

```bash
./mvnw clean package -DskipTests
```

This will create a JAR file in the `target` directory.

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
