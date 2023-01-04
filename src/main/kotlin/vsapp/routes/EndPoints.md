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
(LoginUserDTO)

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
(SignInDTO)

**Response**:

| Code | Message    | Body                 | Header                   |
|------|------------|----------------------|--------------------------|
| 200  | OK         | ``UserDTO/ErrorDTO`` | ``Authorization: Long?`` |
| 400  | BadRequest | ``ErrorDTO``         |                          |
| 409  | Conflict   | ``ErrorDTO``         |                          |
----
### DELETE: user/delete

**Params**:

Authorization: header

**Body**:

```json
{}
```
**Response**:

| Code | Message      | Body         | Header |
|------|--------------|--------------|--------|
| 200  | OK           |              |        |
| 401  | Unauthorized | ``ErrorDTO`` |        |
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
| 403  | Forbidden    | ``ErrorDTO`` |        |
| 404  | NotFound     | ``ErrorDTO`` |        |
---

### POST: /party

**Params**:
- Authorization: header

**Body**:

```json
{
  "order" : [{ "name":"string", "gender":"string" }],
  "family" : [["Long"]],
  "relations" : ["RelationDTO"]
}
```
(PartyDTO)

**Response**:

| Code | Message      | Body         | Header            |
|------|--------------|--------------|-------------------|
| 200  | OK           | ``PartyDTO`` | ``partyId: Long`` |
| 400  | BadRequest   | ``ErrorDTO`` |                   |
| 401  | Unauthorized | ``ErrorDTO`` |                   |
_____
### PUT: /party/{id}/edit

**Params**:
- id: Path
- Authorization: header

**Body**:

```json
{
  "order" : [{ "name":"string", "gender":"string" }],
  "family" : [["Long"]],
  "relations" : ["RelationDTO"]
}
```
**Response**:

| Code | Message      | Body         | Header |
|------|--------------|--------------|--------|
| 200  | OK           | ``PartyDTO`` |        |
| 400  | BadRequest   | ``ErrorDTO`` |        |
| 401  | Unauthorized | ``ErrorDTO`` |        |
| 403  | Forbidden    | ``ErrorDTO`` |        |
| 404  | NotFound     | ``ErrorDTO`` |        |
---
### DELETE: party/{id}/delete

**Params**:

- Authorization: header
- id: Path

**Body**:

```json
{}
```
**Response**:

| Code | Message      | Body         | Header |
|------|--------------|--------------|--------|
| 200  | OK           |              |        |
| 401  | Unauthorized | ``ErrorDTO`` |        |
| 403  | Forbidden    | ``ErrorDTO`` |        |
| 404  | NotFound     | ``ErrorDTO`` |        |
----
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
### GET: challenge/categories

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
(ChallengeRequestDTO)

**Response**:

| Code | Message      | Body             | Header |
|------|--------------|------------------|--------|
| 200  | OK           | ``ChallengeDTO`` |        |
| 400  | BadRequest   | ``ErrorDTO``     |        |
| 401  | Unauthorized | ``ErrorDTO``     |        |
----
### PUT: /challenge/result

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