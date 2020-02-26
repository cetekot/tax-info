Basic Spring Cloud microservices.
===============
Simple Spring Cloud application demo, which includes:
* **DiscoveryService**: the very basic EurekaServer for other microservices to communicate with each other.
* **ConfigService**: simple ConfigServer implementation to handle centralized configurations. Default configuration
location is set to [another my github repo](https://github.com/cetekot/tax-config).
* **AuthenticationService**: provides username/password authentication with generating an access token. This token
must be used accessing TaxService. More users can be added via H2 console or by adding them to `V1.2__Init_data.sql`
script in `resources/db/migrations` of AuthenticationService.
* **TaxService**: my vision of tax service. Provides how much tax should be paid on next income, based on how much
income was received already, how much tax paid already and what are the tax layers. All three are defined in `V1.2__init_data.sql`
script in `resources/db/migrations` of TaxService.

For sample requests please refer to [SAMPLE-REQUESTS.md](SAMPLE-REQUESTS.md) file.