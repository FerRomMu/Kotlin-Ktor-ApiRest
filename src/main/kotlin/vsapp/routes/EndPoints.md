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

| Code | Message | Body                 | Header                   |
|------|---------|----------------------|--------------------------|
| 200  | OK      | ``UserDTO/ErrorDTO`` | ``Authorization: Long?`` |
| 400  | OK      | ``ErrorDTO``         |                          |
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

| Code | Message | Body         | Header |
|------|---------|--------------|--------|
| 200  | OK      | ``PartyDTO`` |        |
| 401  | OK      | ``ErrorDTO`` |        |
| 404  | OK      | ``ErrorDTO`` |        |
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

| Code | Message | Body         | Header            |
|------|---------|--------------|-------------------|
| 200  | OK      | ``PartyDTO`` | ``partyId: Long`` |
| 401  | OK      | ``ErrorDTO`` |                   |
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

| Code | Message | Body         | Header |
|------|---------|--------------|--------|
| 200  | OK      | ``PartyDTO`` |        |
| 401  | OK      | ``ErrorDTO`` |        |
| 403  | OK      | ``ErrorDTO`` |        |
| 404  | OK      | ``ErrorDTO`` |        |
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

| Code | Message | Body          | Header |
|------|---------|---------------|--------|
| 200  | OK      | ``MemberDTO`` |        |
| 401  | OK      | ``ErrorDTO``  |        |
| 403  | OK      | ``ErrorDTO``  |        |
| 404  | OK      | ``ErrorDTO``  |        |
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

| Code | Message | Body              | Header |
|------|---------|-------------------|--------|
| 200  | OK      | ``CategoriesDTO`` |        |
| 401  | OK      | ``ErrorDTO``      |        |
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

| Code | Message | Body             | Header |
|------|---------|------------------|--------|
| 200  | OK      | ``ChallengeDTO`` |        |
| 400  | OK      | ``ErrorDTO``     |        |
| 401  | OK      | ``ErrorDTO``     |        |
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

| Code | Message | Body           | Header |
|------|---------|----------------|--------|
| 200  | OK      | ``PointsDTO``  |        |
| 400  | OK      | ``ErrorDTO``   |        |
| 401  | OK      | ``ErrorDTO``   |        |
| 403  | OK      | ``ErrorDTO``   |        |