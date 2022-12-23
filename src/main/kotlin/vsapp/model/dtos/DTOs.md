# DTOs

### LoginUserDTO
```json
{
  "user": "string",
  "password": "string"
}
```
### SignInDTO
```json
{
  "user": "string",
  "password": "string",
  "email": "string"
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
  "order": [{ "name":"string", "genere":"string", "id": "Long" }],
  "family": [["string"]],
  "relationships": [{ "parejaA": "string", "parejaB": "string" }]
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
### MemberSimplifiedDTO
```json
{
  "id": "Long",
  "name": "string"
}
```
### ChallengeDTO
```json
{
  "id" : "Long",
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
  "partyId": "Long",
  "id" : "Long",
  "points": "Int",
  "acceptedBy": [{ "name": "string", "id": "Long" }],
  "rejectedBy": [{ "name": "string", "id": "Long" }],
  "option": "String?"
}
```
### CategoriesDTO
```json
{
  "categories": [{"name": "string", "description": "string"}]
}
```
### CategoryDTO
```json
{
  "name": "string", 
  "description": "string"
}
```
### PointsDTO

```json
{
  "partyId": "Long",
  "record": "Int",
  "members": [{ "name": "string", "points": "Int" }]
}
```

### ErrorDTO
```json
{
  "errorMessage": "String"
}
```