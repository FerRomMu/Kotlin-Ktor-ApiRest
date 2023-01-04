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
  "id": "Long",
  "user": "String",
  "partiesIds": ["Longs"],
  "email": "String",
  "lastParty": "Long"
}
```
### PartyDTO
```json
{
  "order": ["MemberDTO"],
  "family": [["Long"]],
  "relations": ["RelationDTO"]
}
```
### MemberDTO
```json
{
  "id": "Long",
  "name": "String",
  "gender": "String",
  "points": "Int"
}
```
### MemberSimplifiedDTO
```json
{
  "id": "Long",
  "name": "String"
}
```
### ChallengeDTO
```json
{
  "id" : "Long",
  "title" : "String",
  "body" : "String",
  "options": ["String"],
  "points": "Int",
  "others": ["MemberSimplifiedDTO"]
}
```
### ChallengeResultDTO
```json
{
  "partyId": "Long",
  "id" : "Long",
  "points": "Int",
  "acceptedBy": ["MemberSimplifiedDTO"],
  "rejectedBy": ["MemberSimplifiedDTO"],
  "option": "String?"
}
```
### CategoriesDTO
```json
{
  "categories": ["CategoryDTO"]
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
  "members": ["MemberDTO"]
}
```

### ErrorDTO
```json
{
  "errorMessage": "String"
}
```