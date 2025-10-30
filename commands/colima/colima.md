# Usage

## Install

> Colima [install][colima_install]

```shell
brew install colima
```

### Dependencies

* `capstone`
* `dtc`
* `pcre2`
* `ca-certificates`
* `xz`
* `glib`
* `nettle`
* `p11-kit`
* `jpeg-turbo`
* `libpng`
* `libslirp`
* `libssh`
* `lzo`
* `ncurses`
* `pixman`
* `snappy`
* `vde`
* `lz4`
* `zstd`
* `qemu`
* `lima`

## Instance operation

### docker runtime

```shell
colima --help
colima list
colima start -p docker-profile \
--cpu 4 --memory 6 --disk 30 --arch x86_64 --runtime docker \
--mount /Users/oliver.ocsko/.colima/docker-profile:/docker-profile
```

### containerd runtime

```shell
colima start --cpu 4 --memory 6 --disk 30 --arch x86_64 --runtime containerd --mount /Users/oliver.ocsko/.colima/docker-mount:/var/lib/docker -p docker-three
colima nerdctl install
```

# Related article

* [How Colima Is A Good Alternative To Docker Desktop][colima_a_good_alternative]

# Back to the [README.MD][readme]

[readme]:<../README.MD>

[colima_install]:<https://github.com/abiosoft/colima#installation>

[colima_a_good_alternative]:<https://dev.to/ths83/how-colima-is-a-good-alternative-to-docker-desktop-4ap6#:~:text=To%20do%20so%2C%20Colima%20is%20using%20profiles.%20To,a%20default%20instance%20with%20funny_profile%20as%20profile%20name.>
