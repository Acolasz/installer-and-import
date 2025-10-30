# New realm to Keycloak with OpenLDAP

---

# User federation

## Connect to OpenLDAP and User configuration

| Key                                    | Value                                         |
|:---------------------------------------|:----------------------------------------------|
| Generel options                        | -                                             |
| UI display name                        | openldap                                      |
| Vendor                                 | Other                                         |
| Connection and authentication settings | -                                             |
| Connection URL                         | ldap://openldap.session.svc.cluster.local:389 |
| Bind type                              | simple                                        |
| Bind DN                                | cn=admin,dc=kktyn,dc=hu                       |
| Bind credentials                       | openldap-password (kubernetes secret)         |
| LDAP searching and updating            | -                                             |
| Edit mode                              | WRITABLE                                      |
| Users DN                               | ou=Partners,dc=kktyn,dc=hu                    |
| Username LDAP attribute                | uid                                           |
| RDN LDAP attribute                     | uid                                           |
| UUID LDAP attribute                    | uid                                           |
| User object classes                    | inetOrgPerson                                 |
| Search scope                           | Subtree                                       |
| Synchronization settings               | -                                             |
| Import users                           | ON                                            |
| Sync Registrations                     | ON                                            |

## Group configuration

| Key                            | Value                           |
|:-------------------------------|:--------------------------------|
| Name                           | openldap                        |
| Mapper type                    | group-ldap-mapper               |
| LDAP Groups DN                 | ou=Groups,dc=kktyn,dc=hu        |
| Group Name LDAP Attribute      | cn                              |
| Group Object Classes           | groupOfNames                    |
| Membership LDAP Attribute      | member                          |
| Membership Attribute Type      | DN                              |
| Membership User LDAP Attribute | uid                             |
| Mode                           | IMPORT                          |
| User Groups Retrieve Strategy  | LOAD_GROUPS_BY_MEMBER_ATTRIBUTE |
| Member-Of LDAP Attribute       | memberOf                        |

# Related article

* [Installing OpenLDAP on Kubernetes with Helm][openldap_config]
* [Keycloak and OpenLDAP on Kubernetes][keycloak_config]
* [Grafana OAuth with Keycloak and how to validate a JWT token][grafana_keycloak_config]

[openldap_config]:<https://www.talkingquickly.co.uk/installing-openldap-kubernetes-helm>

[keycloak_config]:<https://www.talkingquickly.co.uk/keycloak-and-openldap-on-kubernetes>

[grafana_keycloak_config]:<https://janikvonrotz.ch/2020/08/27/grafana-oauth-with-keycloak-and-how-to-validate-a-jwt-token/>
