include clojurescript::line
include clojurescript::http_file

# case $operatingsystem {
#     'ubuntu' : { $home_base = '/home'}
#     'Darwin' : { $home_base = '/Users'}
# }
  

class clojurescript::start {
  exec {"update":
    command => "/usr/bin/apt-get update",
  }
}

class clojurescript::packages {

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
}


class clojurescript::clojure_files($user) {
  $home = "/home/${user}"
  #TODO: factor this out

  notify {"Home = ${clojurescript::clojure_files::home}":}
  file { "bin":
    path => "${clojurescript::clojure_files::home}/bin",
    ensure => directory,
    mode => 755,    
  }
  
  line {"bin_on_path":
    file => "${clojurescript::clojure_files::home}/.bashrc",
    line => 'export PATH=$PATH:~/bin',
    ensure => present    
  }

  #TODO: use package once this gets updated properly
  http_file {"lein":
    require => File["bin"],
    url => "https://raw.github.com/technomancy/leiningen/preview/bin/lein",
    path => "${clojurescript::clojure_files::home}/bin/lein",
    owner => $user,
    mode => 777,
  }
}


class clojurescript::dev_env($user) {

  stage { 'start':
    before => Stage['packages'],
  }
  stage { 'packages':
    before => Stage['main']
  }

  class {
    'clojurescript::start': stage => start;
    'clojurescript::packages': stage => packages;
  }

  class {'clojurescript::clojure_files':
    stage => main,
    user => $user
  }

}
