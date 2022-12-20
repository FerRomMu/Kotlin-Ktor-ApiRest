# Contrato para la API

-----
## User

----
### POST: user/login

**Params**:

**Body**:

```json
{
  "user": "string",
  "password": "string"
}
```
**Response**:

| Code | Message    | Body                 | Header                   |
|------|------------|----------------------|--------------------------|
| 200  | OK         | ``UserDTO/ErrorDTO`` | ``Authorization: Long?`` |
| 400  | BadRequest | ``ErrorDTO``         |                          |
----
### GET: user/self

**Params**:
Authorization: header

**Body**:

```json
{}
```
**Response**:

| Code | Message      | Body          | Header |
|------|--------------|---------------|--------|
| 200  | OK           | ``UserDTO``   |        |
| 401  | Unauthorized | ``ErrorDTO``  |        |
----
### POST: user/register

**Params**:

**Body**:

```json
{
  "user": "string",
  "password": "string",
  "email": "string"
}
```
**Response**:

| Code | Message    | Body                 | Header                   |
|------|------------|----------------------|--------------------------|
| 200  | OK         | ``UserDTO/ErrorDTO`` | ``Authorization: Long?`` |
| 400  | BadRequest | ``ErrorDTO``         |                          |
| 409  | Conflict   | ``ErrorDTO``         |                          |
----
## Party

----
### GET: /party/{id}

**Params**:
- id: Path
- Authorization: header

**Body**:

```json
{}
```
**Response**:

| Code | Message      | Body         | Header |
|------|--------------|--------------|--------|
| 200  | OK           | ``PartyDTO`` |        |
| 401  | Unauthorized | ``ErrorDTO`` |        |
| 404  | NotFound     | ``ErrorDTO`` |        |
---

### POST: /party

**Params**:
- Authorization: header

**Body**:

```json
{
  "order" : [{ "name":"string", "genere":"string" }],
  "family" : [["string"]],
  "relationships" : [{ "parejaA": "string", "parejaB": "string" }]
}
```
**Response**:

| Code | Message      | Body         | Header            |
|------|--------------|--------------|-------------------|
| 200  | OK           | ``PartyDTO`` | ``partyId: Long`` |
| 401  | Unauthorized | ``ErrorDTO`` |                   |
_____
### POST: /party/{id}/edit

**Params**:
- id: Path
- Authorization: header

**Body**:

```json
{
  "order" : [{ "name":"string", "genere":"string" }],
  "family" : [["string"]],
  "relationships" : [{ "parejaA": "string", "parejaB": "string" }]
}
```
**Response**:

| Code | Message      | Body         | Header |
|------|--------------|--------------|--------|
| 200  | OK           | ``PartyDTO`` |        |
| 401  | Unauthorized | ``ErrorDTO`` |        |
| 403  | Forbidden    | ``ErrorDTO`` |        |
| 404  | NotFound     | ``ErrorDTO`` |        |
---

## Members

---
### GET: /member/{memberId}

**Params**:
- Authorization: header

**Body**:

```json
{}
```
**Response**:

| Code | Message      | Body          | Header |
|------|--------------|---------------|--------|
| 200  | OK           | ``MemberDTO`` |        |
| 401  | Unauthorized | ``ErrorDTO``  |        |
| 403  | Forbidden    | ``ErrorDTO``  |        |
| 404  | NotFound     | ``ErrorDTO``  |        |
----

## Challenges

---
### GET: /categories

**Params**:
- Authorization: header

**Body**:

```json
{}
```
**Response**:

| Code | Message      | Body              | Header |
|------|--------------|-------------------|--------|
| 200  | OK           | ``CategoriesDTO`` |        |
| 401  | Unauthorized | ``ErrorDTO``      |        |
----
### GET: /challenge

**Params**:
- Authorization: header

**Body**:

```json
{
  "category": "string",
  "memberId": "long",
  "partyId": "long"
}
```
**Response**:

| Code | Message      | Body             | Header |
|------|--------------|------------------|--------|
| 200  | OK           | ``ChallengeDTO`` |        |
| 400  | BadRequest   | ``ErrorDTO``     |        |
| 401  | Unauthorized | ``ErrorDTO``     |        |
----
### PUT: /challengeResult

**params**:
- Authorization: header

**Body**:
```json
{
  "partyId": "Long",
  "code" : "Long",
  "points": "Int",
  "acceptedBy": [{ "name": "string", "id": "Long" }],
  "rejectedBy": [{ "name": "string", "id": "Long" }]
}
```
**Response**:

| Code | Message      | Body           | Header |
|------|--------------|----------------|--------|
| 200  | OK           | ``PointsDTO``  |        |
| 400  | BadRequest   | ``ErrorDTO``   |        |
| 401  | Unauthorized | ``ErrorDTO``   |        |
| 403  | Forbidden    | ``ErrorDTO``   |        |