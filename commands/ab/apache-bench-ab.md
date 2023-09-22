# Install

```shell
apt-get update
apt-get install -y apache2-utils
# Verify
ab -V
```

# Running 'ab' program

```shell
ab -n 100 -c 10 -g out.data -k https://www.apache.org/
ab -n 10000000 -c 500 -k https://www.apache.org/
```

```shell
for i in {1..20}; do 
  echo $i;
  ab -r -q -n 10000 -c10 -k https://www.apache.org/ 2>&1 >ab_test_10000_100_${i}.log & sleep 1;
done
```

# Related website and articles

* Original WebSite: [ab programs][apache_ab]
* Command ab flag and output: [ab command][apache_ab_environment_setup]
* Command ab load testing: [ab command load testing][apache_ab_load_testing]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[apache_ab]:<https://httpd.apache.org/docs/2.4/programs/ab.html>

[apache_ab_environment_setup]:<https://www.tutorialspoint.com/apache_bench/apache_bench_environment_setup.htm>

[apache_ab_load_testing]:<https://diamantidis.github.io/2020/07/15/load-testing-with-apache-bench>