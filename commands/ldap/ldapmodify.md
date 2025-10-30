# Usage

```shell
ldapmodify -x -H ldap://localhost:3890 \
-D "cn=admin,dc=emap,dc=hu" \
-w 31iDTKEindcQf3uxRC0G \
-f ./ldap/02-groups.ldif
```

```shell
ldapmodify -x -H ldap://localhost:3890 \
-D "cn=admin,dc=emap,dc=hu" \
-w 31iDTKEindcQf3uxRC0G \
-f ./ldap/03-users.ldif
```
