include clojurescript::line
include clojurescript::http_file

case $operatingsystem {
    'ubuntu' : { $home_base = '/home'}
    'Darwin' : { $home_base = '/Users'}
}
  


class clojurescript::dev_env($user) {
  $home = "${home_base}/${user}"
  #TODO: factor this out
  
  package { "git-core":
    ensure => present,
  }
  
  package {"libssl-dev":
    ensure => present,
  }

  package {"unzip":
    ensure => present,
  }
  
  package {"jdk":
    name => "openjdk-7-jre",
    ensure => present,
  }

  file { "bin":
    path => "${clojurescript::dev_env::home}/bin",
    ensure => directory,
    mode => 755,    
  }
  
  line {"bin_on_path":
    file => "${clojurescript::dev_env::home}/.bashrc",
    line => 'export PATH=$PATH:~/bin',
    ensure => present    
  }

  #TODO: use package once this gets updated properly
  http_file {"lein":
    require => File["bin"],
    url => "https://raw.github.com/technomancy/leiningen/preview/bin/lein",
    path => "${clojurescript::dev_env::home}/bin/lein",
    owner => $user,
    mode => 777,
  }
}

