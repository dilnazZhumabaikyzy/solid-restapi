# Solid Bank REST
The project simulates various banking transactions, including deposits, withdrawals as well as account CRUD using the RESTAPI.

## Prerequisites
Java 8, Maven, Spring Tool Suite, IntelliJ IDEA
## Local Setup
1. Clone the repository:
   ```
   git@github.com:dilnazZhumabaikyzy/solid-restapi.git
   ```
2. Build:
   ```
   mvn clean install
   ```
3. Run:
   ```
   mvn spring-boot:run
   ```
## API
Swagger documentation:
   http://localhost:8080/swagger-ui/index.html#/

#### Get all accounts from DB. Authority ADMIN
 - GET: /accounts
#### Get accounts for authenticated user
 - GET: /accounts/client
#### Create an account
 - POST: /accounts
#### Withdraw money amount from account_id
 - POST: /accounts/{account_id}/withdraw?amount=
#### Deposit money amount for account_id
 - POST: /accounts/{account_id}/deposit?amount=
#### Get account with ID: account_id
 - GET: /accounts/{account_id}
#### Delete account with ID: account_id
 - DELETE: /accounts/{account_id}
#### Get all transactions from DB
 - GET: /accounts/transactions
#### Get all transactions of client with ID: client_id
 - GET: /accounts/transactions/{client_id}