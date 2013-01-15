class recipes($user) {
  include clojurescript::dev_env
  exec {"recipes":
    require => Package["git-core"],
    cwd => $home,
    #TODO: move this elsewhere
    command => "/usr/bin/git clone https://github.com/andrewmains12/recipes.git",
    creates => "${home}/recipes"
  }

  file {"repo-chown":
    require => Exec["recipes"],
    owner => $user,
    recurse => true,
    path => "${home}/recipes"
  }

}

include recipes
