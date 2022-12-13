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

| Code | Message | Body        | Header                  |
|------|---------|-------------|-------------------------|
| 200  | OK      | ``UserDTO`` | ``Authorization: Long`` |

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