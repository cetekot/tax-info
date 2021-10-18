### Logging in to get auth token:

Send the following JSON via POST to `AUTHENTICATION_SERVICE_URL/api/v1/auth`
```json
{
	"username": "John",
	"password": "Smith"
}
```
You should get back a valid token.

Note, that user `John` with password `Smith` is predefined unless you've changed `V1.2__Init_data.sql` script
in `resources/db/migrations`.

### Checking if token is valid

To check token validity send GET request to the following url `AUTHENTICATION_SERVICE_URL/api/v1/auth/<YOUR_TOKEN_HERE>`.

Tokens will be valid for 10 minutes, though value can be changed in `V1.2__Init_data.sql` script in `resources/db/migrations`
or directly in `AUTHENTICATION_SERVICE_URL` database.

### Getting tax amount advise:
Sent the following JSON via POST to `<TAX_SERVICE_URL>/api/v1/tax/simple`
```json
{
    "taxpayerId": "1234567890",
    "income": 765,
    "receivedAt": "2020-03-12T10:25:43.511Z",
    "isChecking": "true"
}
```
Request should have header called `AuthToken` with token value from authorization.

You should receive something like:
```json
{
    "taxpayerId": "1234567890",
    "taxPayable": 84.7485
}
```
If tax payer ID does not exist in the system, it will be treated as the person exists,
but didn't have any income yet and didn't pay any taxes yet.