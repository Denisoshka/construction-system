classDiagram
direction LR
class apartment_house {
   integer floors
   integer project_id
}
class bridge {
   integer span
   integer width
   integer traffic_lanes_number
   integer project_id
}
class brigade_table {
   integer foreman_id
   integer id
}
class construction_management {
   varchar(100) name
   text address
   varchar(50) phone
   integer construction_manager_id
   integer id
}
class construction_project {
   integer id
   integer site_id
}
class construction_project_contract {
   integer project_id
   integer site_id
   integer customer_organization_id
   varchar(150) type
   date signing_date
   date plan_start_date
   date plan_end_date
   date fact_start_date
   date fact_end_date
   integer id
}
class construction_site {
   varchar(255) name
   text address
   varchar(50) phone
   integer management_id
   integer site_manager_id
   integer id
}
class customer_organization {
   varchar(100) name
   integer id
}
class databasechangelog {
   varchar(255) id
   varchar(255) author
   varchar(255) filename
   timestamp dateexecuted
   integer orderexecuted
   varchar(10) exectype
   varchar(35) md5sum
   varchar(255) description
   varchar(255) comments
   varchar(255) tag
   varchar(20) liquibase
   varchar(255) contexts
   varchar(255) labels
   varchar(10) deployment_id
}
class databasechangeloglock {
   boolean locked
   timestamp lockgranted
   varchar(255) lockedby
   integer id
}
class employees {
   varchar(100) name
   varchar(100) surname
   varchar(100) patronymic
   date seniority
   integer id
}
class engineer_position {
   varchar(100) name
   integer id
}
class engineers {
   integer position_id
   integer employee_id
}
class estimate {
   date last_update_date
   integer id
}
class foremen_team_management_table {
   integer teamid
   integer workerid
}
class machinery_management {
   integer machinery_id
   integer site_id
   integer project_id
   date start_date
   date end_date
   integer id
}
class machinery_type {
   varchar(150) name
   integer id
}
class machinery_unit {
   integer type_id
   integer management_id
   varchar(150) manufacturer
   varchar(150) model
   varchar(17) vin_number
   integer id
}
class material_type {
   varchar(150) name
   integer cost
   integer id
}
class material_usage {
   integer plan_quantity
   integer fact_quantity
   integer estimate_id
   integer material_id
}
class object_types {
   varchar(150) type
}
class report_table {
   integer contract_id
   date report_creation_date
   json report_file
   integer id
}
class school {
   integer classroom_count
   integer floors
   integer project_id
}
class site_head_team_management_table {
   integer teamid
   integer engineerid
}
class site_team_table {
   integer site_head_id
   integer id
}
class work_schedule {
   integer brigade_id
   integer work_type_id
   date plan_start_date
   date plan_end_date
   date fact_start_date
   date fact_end_date
   integer plan_order
   integer fact_order
   integer contract_id
   integer id
}
class work_type {
   varchar(100) name
   integer id
}
class worker_position {
   varchar(100) name
   integer id
}
class workers {
   integer position_id
   integer employee_id
}

apartment_house  -->  construction_project_contract : project_id:id
bridge  -->  construction_project_contract : project_id:id
brigade_table  -->  workers : foreman_id:employee_id
construction_management  -->  engineers : construction_manager_id:employee_id
construction_project  -->  construction_site : site_id:id
construction_project_contract  -->  construction_project : project_id, site_id:id, site_id
construction_project_contract  -->  customer_organization : customer_organization_id:id
construction_project_contract  -->  object_types : type
construction_site  -->  construction_management : management_id:id
construction_site  -->  employees : site_manager_id:id
engineers  -->  employees : employee_id:id
engineers  -->  engineer_position : position_id:id
estimate  -->  construction_project_contract : id
foremen_team_management_table  -->  site_team_table : teamid:id
foremen_team_management_table  -->  workers : workerid:employee_id
machinery_management  -->  construction_project : project_id, site_id:id, site_id
machinery_management  -->  machinery_unit : machinery_id:id
machinery_unit  -->  construction_management : management_id:id
machinery_unit  -->  machinery_type : type_id:id
material_usage  -->  estimate : estimate_id:id
material_usage  -->  material_type : material_id:id
report_table  -->  construction_project_contract : contract_id:id
school  -->  construction_project_contract : project_id:id
site_head_team_management_table  -->  engineers : engineerid:employee_id
site_head_team_management_table  -->  site_team_table : teamid:id
site_team_table  -->  employees : site_head_id:id
work_schedule  -->  brigade_table : brigade_id:id
work_schedule  -->  construction_project_contract : contract_id:id
work_schedule  -->  work_type : work_type_id:id
workers  -->  employees : employee_id:id
workers  -->  worker_position : position_id:id
