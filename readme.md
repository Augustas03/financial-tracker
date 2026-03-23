# Personal Finance Tracker API

A RESTful API built with Spring Boot to manage and track personal expenses using an in-memory h2 database

---

## API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| GET | `/api/transactions` | List all transactions |
| POST | `/api/transactions` | Create a new transaction |
| DELETE | `/api/transactions/{id}` | Delete a specific transaction |
| GET | `/api/transactions/total` | Calculate total for current month |

### Example POST Body
```json
{
  "description": "Gym Membership",
  "amount": 45.00,
  "category": "GYM"
}