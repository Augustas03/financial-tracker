# Personal Finance Tracker API

A RESTful API built with Spring Boot to manage and track personal expenses using an in-memory h2 database

---

## API Endpoints

| Method | Endpoint                        | Description                       |
|:-------|:--------------------------------|:----------------------------------|
| GET    | `/api/transactions`             | List all transactions             |
| GET    | `/api/transactions/{id}`        | Get transactions for user id      |
| POST   | `/api/transactions/{id}`        | Create a new transaction          |
| DELETE | `/api/transactions/{id}`        | Delete a specific transaction     |
| GET    | `/api/transactions/total-month` | Calculate total for current month |
| GET    | `/api/transactions/total`       | Calculate total for all time      |
| GET    | `/users`                        | create a user                     |
| GET    | `/users/{id}`                   | get a user for given id           |

### Example POST Body for creating a transaction `/api/transactions/{id}`
```json

{
  "description": "Gym Membership",
  "amount": 45.00,
  "category": "GYM",
}