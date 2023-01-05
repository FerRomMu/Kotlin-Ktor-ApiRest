# DTOs

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
### ErrorDTO
```json
{
  "errorMessage": "String"
}
```
### LoginUserDTO
```json
{
  "user": "string",
  "password": "string"
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
### PartyDTO
```json
{
  "order": ["MemberDTO"],
  "family": [["Long"]],
  "relations": ["RelationDTO"]
}
```
### RelationDTO
```json
{
  "id1": "Long",
  "id2": "Long"
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