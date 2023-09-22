# Commands

```shell
# cpu
sysbench --test=cpu help

sysbench --test=cpu run
sysbench --test=cpu --cpu-max-prime=20000 run

# memory
sysbench --test=memory help

sysbench --test=memory run

# fileio
sysbench --test=fileio help

sysbench --test=fileio --file-test-mode=seqwr run
sysbench --test=fileio --file-total-size=100G cleanup
```

Related articles
* [How to Use Sysbench for Linux Performance Testing?][testing]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[testing]:<https://linuxhint.com/use-sysbench-for-linux-performance-testing/>
