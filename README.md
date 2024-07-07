# PROGLOGIC search engine

This project focuses on managing device families and associated lifecycle phases, suppliers, Programmable Logic
Devices (PLD) types and status information using a Spring Boot framework with JPA.

## Usage

This project facilitates the management of device families, suppliers, PLD types, and lifecycle phases through a RESTful
API, ensuring efficient data handling and validation. It leverages Spring Boot’s capabilities for rapid development and
JPA for seamless database interactions.

### API endpoints

#### Device Families

* Get All Device Families
    * `GET /api/v1/device-families`
    * Retrieves all device families.

* Add New Device Family
    * `POST /api/v1/device-families/addDeviceFamily`
    * Adds a new device family. Example:

 ```json
{
  "familyName": "ExampleFamily",
  "familyPrefix": "EX",
  "supplier": {
    "supplierName": "ExampleSupplier"
  },
  "pldType": {
    "pldTypeName": "FPGA"
  },
  "lifecyclePhase": {
    "lifecycleName": "NEW"
  },
  "status": {
    "status_name": "NO NEW USE"
  },
  "nextAnnualReview": 2025
}
```

* Get Device Family by Part Number
    * `GET /api/v1/device-families/{partNumber}`
    * Retrieves a device family by its part number.

#### Lifecycle Phases

* Get All Lifecycle Phases
    * `GET /api/v1/lifecycle-phases`
    * Retrieves all lifecycle phases.

* Add New Lifecycle Phase
    * `POST /api/v1/lifecycle-phases/addLifecyclePhase`
    * Adds a new lifecycle phase. Example:

```json
{
  "pldTypeName": "NewType"
}
```

#### Suppliers

* Get All Suppliers
    * `GET /api/v1/suppliers`
    * Retrieves all suppliers.

* Add New Supplier
    * `POST /api/v1/suppliers/addSupplier`
    * Adds a new supplier. Example:

```json
{
  "supplierName": "NewSupplier"
}
```

#### Teradyne Statuses

* Get All Teradyne Statuses
    * `GET /api/v1/ter-statuses`
    * Retrieves all Teradyne statuses.

* Add New Teradyne Status
    * `POST /api/v1/ter-statuses/addStatus`
    * Adds a new Teradyne status. Example:

```json
{
  "status_name": "NewStatus"
}
```

### Database information

This project was developed using MariaDB relational database. However, you can use any other database engine you want.
Just make
sure to include it in the dependencies (see the `pom.xml` file).

For example, to use MySQL instead, make sure to do the following:

1. Include the MySQL connector Java as part of the project dependencies:

```xml

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

2. Update the `application.properties.template` file with the information required so the application can use the
   connector:

```properties
spring.datasource.url=jdbc:mysql://localhost:<PORT>/<DATABASE_NAME>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

**NOTE**: The project includes a template for the `application.properties` file. Make sure to remove the `.template` at
the end to make it work correctly.

## Documentation

The documentation for this project can be found in the designated `docs` directory: `./src/main/docs`