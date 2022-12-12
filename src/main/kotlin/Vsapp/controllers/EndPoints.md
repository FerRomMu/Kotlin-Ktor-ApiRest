# Contrato para la API

-----
## GET

----
### Path: /member/{memberId}

**Params**:
- Authorization: header

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
### Path: /member/{memberId}

**Params**:
- memberId: path
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

## POST

----
### Path: /user

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
### Path: /party

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
### Path: /party/{id}

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

| Code | Message | Body         | Header            |
|------|---------|--------------|-------------------|
| 200  | OK      | ``PartyDTO`` | ``partyId: Long`` |

## PUT

----
### Path: /party/{id}

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

| Code | Message | Body         | Header            |
|------|---------|--------------|-------------------|
| 200  | OK      | ``PartyDTO`` | ``partyId: Long`` |

## DELETE

----