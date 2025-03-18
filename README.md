<p align="center">
  <h1 align="center">Query DSL</h1>
  <p align="center">A java DSL to represent filter expressions<p>
  <p align="center">
    <a href="https://github.com/PhonePe/query-dsl/actions">
    	<img src="https://github.com/PhonePe/query-dsl/actions/workflows/actions.yml/badge.svg"/>
    </a>
    <a href="https://s01.oss.sonatype.org/content/repositories/releases/com/phonepe/query-dsl/">
    	<img src="https://img.shields.io/maven-central/v/com.phonepe.commons/query-dsl"/>
    </a>
    <a href="https://github.com/PhonePe/query-dsl/blob/master/LICENSE">
    	<img src="https://img.shields.io/github/license/PhonePe/query-dsl" alt="license" />
    </a></p>
  <p align="center">
    <a href="https://sonarcloud.io/project/overview?id=PhonePe_query-dsl">
    	<img src="https://sonarcloud.io/api/project_badges/measure?project=PhonePe_query-dsl&metric=alert_status"/>
    </a>
    <a href="https://sonarcloud.io/project/overview?id=PhonePe_query-dsl">
    	<img src="https://sonarcloud.io/api/project_badges/measure?project=PhonePe_query-dsl&metric=coverage"/>
    </a>
    <a href="https://sonarcloud.io/project/overview?id=PhonePe_query-dsl">
    	<img src="https://sonarcloud.io/api/project_badges/measure?project=PhonePe_query-dsl&metric=bugs"/>
    </a>
    <a href="https://sonarcloud.io/project/overview?id=PhonePe_query-dsl">
    	<img src="https://sonarcloud.io/api/project_badges/measure?project=PhonePe_query-dsl&metric=vulnerabilities"/>
    </a>
  </p>
</p>

## Overview

Query DSL provides a simple way to build and represent filter expressions for querying application data. It supports a
wide range of filter types, from simple equality checks to complex logical combinations, making it suitable for various
use cases such as database queries, search functionality, and data filtering.

## Motivation

Filter expressions are commonly used in applications to query and filter data based on criteria that is application
specific. Think of this as a way of representing complex application-specific queries in a structured and reusable
manner. While your application hides the complexity and the implementation details of the query, the query DSL provides
a way to represent these queries in a structured and type-safe manner.

## Features

- **Rich Filter Types**: Supports various filter operations including equality, comparison, pattern matching, and more
- **Logical Operators**: Combine filters using AND, OR, and NOT operators
- **Type Safety**: Strongly typed filter classes for compile-time safety
- **Visitor Pattern**: Extensible architecture using the visitor pattern for custom filter processing
- **JSON Serialization**: Built-in support for JSON serialization/deserialization using Jackson
- **Validation**: Filter validation to ensure correctness

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Installation

Add the dependency to your project:

#### Maven

```xml

<dependency>
    <groupId>com.phonepe.commons</groupId>
    <artifactId>query-dsl</artifactId>
    <version>1.4</version>
</dependency>
```

## Filter Types

### General Filters

| Filter Type     | Description                                | Example JSON                                                                      |
|-----------------|--------------------------------------------|-----------------------------------------------------------------------------------|
| EqualsFilter    | Checks if a field equals a value           | `{"operator": "EQUALS", "field": "name", "value": "John Doe"}`                    |
| NotEqualsFilter | Checks if a field does not equal a value   | `{"operator": "NOT_EQUALS", "field": "status", "value": "inactive"}`              |
| InFilter        | Checks if a field's value is in a list     | `{"operator": "IN", "field": "category", "values": ["electronics", "gadgets"]}`   |
| NotInFilter     | Checks if a field's value is not in a list | `{"operator": "NOT_IN", "field": "status", "values": ["deleted", "archived"]}`    |
| ExistsFilter    | Checks if a field exists                   | `{"operator": "EXISTS", "field": "email"}`                                        |
| MissingFilter   | Checks if a field is missing               | `{"operator": "MISSING", "field": "phoneNumber"}`                                 |
| ContainsFilter  | Checks if a field contains a value         | `{"operator": "CONTAINS", "field": "tags", "value": "premium", "iterable": true}` |
| AnyFilter       | Matches any value for a field              | `{"operator": "ANY"`                                                              |
| GenericFilter   | Custom filter with arbitrary structure     | `{"operator": "GENERIC", "field": "customField", "value": {"key": "value"}}`      |

### Logical Filters

| Filter Type | Description                       | Example JSON                            |
|-------------|-----------------------------------|-----------------------------------------|
| AndFilter   | Combines filters with logical AND | `{"operator": "AND", "filters": [...]}` |
| OrFilter    | Combines filters with logical OR  | `{"operator": "OR", "filters": [...]}`  |
| NotFilter   | Negates a filter                  | `{"operator": "NOT", "filter": {...}}`  |

### Numeric Filters

| Filter Type        | Description                                           | Example JSON                                                    |
|--------------------|-------------------------------------------------------|-----------------------------------------------------------------|
| GreaterThanFilter  | Checks if a field is greater than a value             | `{"operator": "GREATER_THAN", "field": "age", "value": 18}`     |
| GreaterEqualFilter | Checks if a field is greater than or equal to a value | `{"operator": "GREATER_EQUAL", "field": "score", "value": 75}`  |
| LessThanFilter     | Checks if a field is less than a value                | `{"operator": "LESS_THAN", "field": "price", "value": 100}`     |
| LessEqualFilter    | Checks if a field is less than or equal to a value    | `{"operator": "LESS_EQUAL", "field": "quantity", "value": 10}`  |
| BetweenFilter      | Checks if a field is between two values               | `{"operator": "BETWEEN", "field": "age", "from": 18, "to": 65}` |

### String Filters

| Filter Type            | Description                                      | Example JSON                                                                                  |
|------------------------|--------------------------------------------------|-----------------------------------------------------------------------------------------------|
| StringStartsWithFilter | Checks if a string field starts with a value     | `{"operator": "STRING_STARTS_WITH", "field": "name", "value": "John", "offset": 0}`           |
| StringEndsWithFilter   | Checks if a string field ends with a value       | `{"operator": "STRING_ENDS_WITH", "field": "email", "value": "@example.com"}`                 |
| StringRegexMatchFilter | Checks if a string field matches a regex pattern | `{"operator": "STRING_REGEX_MATCH", "field": "phoneNumber", "value": "\\d{3}-\\d{3}-\\d{4}"}` |

## Usage Examples

### Building Filters Programmatically

```java
// Create a simple equals filter
EqualsFilter statusFilter = EqualsFilter.builder()
                .field("status")
                .value("active")
                .build();

// Create a greater than filter
GreaterThanFilter ageFilter = GreaterThanFilter.builder()
        .field("age")
        .value(18)
        .build();

// Combine filters with AND
AndFilter combinedFilter = AndFilter.builder()
        .filter(statusFilter)
        .filter(ageFilter)
        .build();
```

Your main implementation of the filter logic in your application (where you might query some underlying data-store)
would have to implement the `FilterVisitor` interface and provide the logic for each filter type.

```java
public class DBQueryVisitor implements FilterVisitor<DBQuery> {
    @Override
    public DBQuery visit(EqualsFilter equalsFilter) {
        // Implement the logic for the equals filter
        return new DBQuery..;
    }

    @Override
    public DBQuery visit(GreaterThanFilter greaterThanFilter) {
        // Implement the logic for the greater than filter
        return new DBQuery..;
    }

    // Implement other visit methods...
}

```

### Serializing to JSON

```java
ObjectMapper objectMapper = new ObjectMapper();
String json = objectMapper.writeValueAsString(combinedFilter);
```

### Deserializing from JSON

```java
String json = "{"
        + "\"operator\": \"AND\","
        + "\"filters\": ["
        + "  {\"operator\": \"EQUALS\", \"field\": \"status\", \"value\": \"active\"},"
        + "  {\"operator\": \"GREATER_THAN\", \"field\": \"age\", \"value\": 18}"
        + "]}";

Filter filter = objectMapper.readValue(json, Filter.class);
```

### Using the Visitor Pattern

```java
// Create a visitor implementation
FilterVisitor<Boolean> evaluationVisitor = new AbstractFilterVisitor<Boolean>(false) {
            @Override
            public Boolean visit(EqualsFilter equalsFilter) {
                // Implementation for equals filter
                return getValue(equalsFilter.getField()).equals(equalsFilter.getValue());
            }

            // Implement other visit methods...
        };

// Apply the visitor to a filter
boolean result = filter.accept(evaluationVisitor);
```

## Testing

The project includes comprehensive tests for all filter types:

- **FilterModelTest**: Unit tests for all filter model classes
- **FilterDeserializationTest**: Parameterized tests for JSON deserialization of all filter types
- **FilterCounterTest**: Tests for the filter counting functionality
- **FilterEvalTest**: Tests for filter evaluation

Run the tests using Maven:

```bash
mvn test
```

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
