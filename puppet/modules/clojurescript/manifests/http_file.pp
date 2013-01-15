define clojurescript::http_file($url,
$mode=755, $owner=$id, $path=$title,
$require=present)
{
  
  #A file accessible over http; will be accessed with wget
  package { "${title}_wget":
    ensure => present,
    name => "wget"
  }

  $tmp_name = "/tmp/${title}"
  exec { "${title}_get_file":
    require => [Package["${title}_wget"]],
    command => "wget $url -O ${tmp_name}",
    path => ["/usr/bin"],
    creates => $tmp_name,
    #    ensure => present,
    #TODO: change this to use package manager once lein2 is standard
  }

  file { "${title}_install_file":
    require => [Exec["${title}_get_file"]],
    source => "file://${tmp_name}",
    mode => $mode,
    path => $path,
    owner => $owner,
  }

  tidy { '${title}_clean':
    require => [File["${title}_install_file"]],
    path => $tmp_name
  }

  # Package['$title_wget'] ->
  # Exec['$title_get_file'] ->
  # File['$title_install_file'] -> Tidy['$title_clean']
}
