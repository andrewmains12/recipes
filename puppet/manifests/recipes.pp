class recipes($user) {

  $home = "/home/${user}"
  class {'clojurescript::dev_env':
    user => $user
  }

  package {"ubuntu-desktop":
    ensure => present,    
  }
  
  exec {"recipes":
    require => Package["git-core"],
    cwd => $recipes::home,
    #TODO: move this elsewhere
    command => "/usr/bin/git clone https://github.com/andrewmains12/recipes.git",
    creates => "${recipes::home}/recipes"
  }

  file {"repo-chown":
    require => Exec["recipes"],
    owner => $user,
    recurse => true,
    path => "${recipes::home}/recipes"
  }

}



class {'recipes':
  user => 'vagrant',
}

