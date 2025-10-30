# Usage

```shell
ldapadd -x -H ldap://localhost:3890 \
-D "cn=admin,dc=emap,dc=hu" \
-w 626fzJQThN9i38kH6l2o \
-f ./ldap/01-domain.ldif
```

```shell
ldapadd -x -H ldap://localhost:3890 \
-D "cn=admin,dc=emap,dc=hu" \
-w 626fzJQThN9i38kH6l2o \
-f ./021-groups.ldif
```

```shell
ldapadd -x -H ldap://localhost:3890 \
-D "cn=admin,dc=emap,dc=hu" \
-w 626fzJQThN9i38kH6l2o \
-f ./ldap/03-users.ldif
```
