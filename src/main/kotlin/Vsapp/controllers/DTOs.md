# DTOs

### UserLoginDTO
```json
{
  "user": "string",
  "password": "string"
}
```
### UserDTO
```json
{
  "user": "name",
  "partiesIds": ["Longs"],
  "lastParty": { "partyId": "Long", "party": "PartyDTO"}
}
```
### PartyDTO
```json
{
  "order": [{ "name":"string", "genere":"string" }],
  "family": [["string"]],
  "relationships": [{ "parejaA": "string", "parejaB": "string" }]
}
```
### PartyLoginDTO
```json
{
  "partyId": "Long"
}
```
### MemberDTO
```json
{
  "id": "Long",
  "name": "string",
  "points": "Int"
}
```
### SecretDTO
```json
{
  "idMember": "Long",
  "code": "Long",
  "textData": "String",
  "option": "Int"
}
```
### ChallengeDTO
```json
{
  "code" : "Long",
  "title" : "string",
  "body" : "string",
  "options": ["string"],
  "points": "Int",
  "others": [{ "name": "string", "id": "Long" }]
}
```
### ChallengeResultDTO
```json
{
  "code" : "Long",
  "points": "Int",
  "acceptedBy": [{ "name": "string", "id": "Long" }],
  "rejectedBy": [{ "name": "string", "id": "Long" }]
}
```
### CategoriesDTO
```json
{
    "categories": [{"name": "string", "description": "string"}]
}
```
-----