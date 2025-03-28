export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
  Date: { input: Date; output: Date; }
};

export type ApartmentHouseProject = ProjectType & {
  readonly __typename?: 'ApartmentHouseProject';
  readonly floors: Scalars['Int']['output'];
  readonly projectId: Scalars['ID']['output'];
};

export type ApartmentHouseProjectContract = {
  readonly __typename?: 'ApartmentHouseProjectContract';
  readonly contract: ProjectContract;
  readonly project: ApartmentHouseProject;
};

export type ApartmentHouseProjectInput = {
  readonly floors: Scalars['Int']['input'];
};

export type BridgeProject = ProjectType & {
  readonly __typename?: 'BridgeProject';
  readonly projectId: Scalars['ID']['output'];
  readonly span: Scalars['Int']['output'];
  readonly trafficLanesNumber: Scalars['Int']['output'];
  readonly width: Scalars['Int']['output'];
};

export type BridgeProjectContract = {
  readonly __typename?: 'BridgeProjectContract';
  readonly contract: ProjectContract;
  readonly project: BridgeProject;
};

export type BridgeProjectInput = {
  readonly span: Scalars['Int']['input'];
  readonly trafficLanesNumber: Scalars['Int']['input'];
  readonly width: Scalars['Int']['input'];
};

export type Brigade = {
  readonly __typename?: 'Brigade';
  readonly foreman: WorkerInfo;
  readonly id: Scalars['ID']['output'];
  readonly siteInfo: ConstructionSite;
};

export type BrigadeInput = {
  readonly foremanId: Scalars['ID']['input'];
  readonly siteTeamID: Scalars['ID']['input'];
};

export type ConstructionProject = {
  readonly __typename?: 'ConstructionProject';
  readonly id: Scalars['ID']['output'];
  readonly siteID: Scalars['ID']['output'];
};

export type ConstructionSite = {
  readonly __typename?: 'ConstructionSite';
  readonly address: Scalars['String']['output'];
  readonly id: Scalars['ID']['output'];
  readonly name: Scalars['String']['output'];
  readonly phone: Scalars['String']['output'];
  readonly siteManager?: Maybe<EngineerInfo>;
};

export type ConstructionSiteInput = {
  readonly address: Scalars['String']['input'];
  readonly managementId?: InputMaybe<Scalars['ID']['input']>;
  readonly name: Scalars['String']['input'];
  readonly phone: Scalars['String']['input'];
  readonly siteManagerId?: InputMaybe<Scalars['ID']['input']>;
};

export type ContractFilter = {
  readonly projectType?: InputMaybe<Scalars['String']['input']>;
};

export type CustomerOrganization = {
  readonly __typename?: 'CustomerOrganization';
  readonly id: Scalars['ID']['output'];
  readonly name: Scalars['String']['output'];
};

export type CustomerOrganizationInput = {
  readonly name?: InputMaybe<Scalars['String']['input']>;
};

export type Employee = {
  readonly employmentDate: Scalars['Date']['output'];
  readonly id: Scalars['ID']['output'];
  readonly name: Scalars['String']['output'];
  readonly patronymic?: Maybe<Scalars['String']['output']>;
  readonly surname: Scalars['String']['output'];
  readonly systemId: Scalars['String']['output'];
};

export type EmployeeFilter = {
  readonly post?: InputMaybe<Scalars['String']['input']>;
};

export type EmployeeInfo = Employee & {
  readonly __typename?: 'EmployeeInfo';
  readonly employmentDate: Scalars['Date']['output'];
  readonly id: Scalars['ID']['output'];
  readonly name: Scalars['String']['output'];
  readonly patronymic?: Maybe<Scalars['String']['output']>;
  readonly post: JobPost;
  readonly surname: Scalars['String']['output'];
  readonly systemId: Scalars['String']['output'];
};

export type EmployeeInput = {
  readonly name: Scalars['String']['input'];
  readonly patronymic?: InputMaybe<Scalars['String']['input']>;
  readonly surname: Scalars['String']['input'];
  readonly systemId: Scalars['String']['input'];
};

export type EngineerFilter = {
  readonly posId?: InputMaybe<Scalars['Int']['input']>;
};

export type EngineerInfo = {
  readonly __typename?: 'EngineerInfo';
  readonly employee: EmployeeInfo;
  readonly positionInfo: EngineerPosition;
};

export type EngineerPosition = {
  readonly __typename?: 'EngineerPosition';
  readonly id: Scalars['Int']['output'];
  readonly name: Scalars['String']['output'];
};

export type EngineerPositionInput = {
  readonly name: Scalars['String']['input'];
};

export type JobPost =
  | 'ENGINEER'
  | 'UNKNOWN'
  | 'WORKER';

export type Manufacturer = {
  readonly __typename?: 'Manufacturer';
  readonly id: Scalars['ID']['output'];
  readonly manufacturer: Scalars['String']['output'];
};

export type ManufacturerInput = {
  readonly manufacturer: Scalars['String']['input'];
};

export type Material = {
  readonly __typename?: 'Material';
  readonly cost: Scalars['Int']['output'];
  readonly id: Scalars['ID']['output'];
  readonly manufacturerId: Scalars['ID']['output'];
  readonly name: Scalars['String']['output'];
};

export type MaterialInput = {
  readonly cost: Scalars['Int']['input'];
  readonly manufacturer: Scalars['ID']['input'];
  readonly name: Scalars['String']['input'];
};

export type MaterialUsage = {
  readonly __typename?: 'MaterialUsage';
  readonly factQuantity: Scalars['Int']['output'];
  readonly materialID: Scalars['ID']['output'];
  readonly planQuantity: Scalars['Int']['output'];
  readonly workUnitId: Scalars['ID']['output'];
};

export type MaterialUsageInput = {
  readonly factQuantity: Scalars['Int']['input'];
  readonly materialID: Scalars['ID']['input'];
  readonly planQuantity: Scalars['Int']['input'];
  readonly workUnitId: Scalars['ID']['input'];
};

export type Mutation = {
  readonly __typename?: 'Mutation';
  readonly addApartmentHouseProjectContract: ProjectContract;
  readonly addBridgeProjectContract: ProjectContract;
  readonly addSchoolProjectContract: ProjectContract;
  readonly addWorkScheduleUnit: WorkScheduleUnit;
  readonly addWorkerInBrigade?: Maybe<Scalars['Boolean']['output']>;
  readonly createBrigade: Brigade;
  readonly createCustomerOrganization: CustomerOrganization;
  readonly createEmployee: EmployeeInfo;
  readonly createEngineerPosition: EngineerPosition;
  readonly createManufacturer: Manufacturer;
  readonly createMaterialType: Material;
  readonly createWorkerPosition: WorkerPosition;
  readonly deleteApartmentHouseProjectContract: Scalars['Boolean']['output'];
  readonly deleteBridgeProjectContract: Scalars['Boolean']['output'];
  readonly deleteBrigade: Scalars['Boolean']['output'];
  readonly deleteCustomerOrganization: Scalars['Boolean']['output'];
  readonly deleteEmployee: Scalars['Boolean']['output'];
  readonly deleteEngineerPosition: Scalars['Boolean']['output'];
  readonly deleteManufacturer: Scalars['Boolean']['output'];
  readonly deleteMaterialType: Scalars['Boolean']['output'];
  readonly deleteSchoolProjectContract: Scalars['Boolean']['output'];
  readonly deleteWorkScheduleUnit: Scalars['Boolean']['output'];
  readonly deleteWorkerPosition: Scalars['Boolean']['output'];
  readonly updateCustomerOrganization: CustomerOrganization;
  readonly updateEmployee: EmployeeInfo;
  readonly updateEngineerPosition: EngineerPosition;
  readonly updateWorkScheduleUnit: WorkScheduleUnit;
  readonly updateWorkerPosition: EngineerPosition;
};


export type MutationAddApartmentHouseProjectContractArgs = {
  input: ProjectContractInput;
  project?: InputMaybe<ApartmentHouseProjectInput>;
};


export type MutationAddBridgeProjectContractArgs = {
  input: ProjectContractInput;
  project?: InputMaybe<BridgeProjectInput>;
};


export type MutationAddSchoolProjectContractArgs = {
  input: ProjectContractInput;
  project?: InputMaybe<SchoolProjectInput>;
};


export type MutationAddWorkScheduleUnitArgs = {
  input: WorkScheduleUnitInput;
};


export type MutationAddWorkerInBrigadeArgs = {
  BrigadeID: Scalars['ID']['input'];
  workerId: Scalars['ID']['input'];
};


export type MutationCreateBrigadeArgs = {
  input: BrigadeInput;
};


export type MutationCreateCustomerOrganizationArgs = {
  input: CustomerOrganizationInput;
};


export type MutationCreateEmployeeArgs = {
  input: EmployeeInput;
};


export type MutationCreateEngineerPositionArgs = {
  input: EngineerPositionInput;
};


export type MutationCreateManufacturerArgs = {
  input: ManufacturerInput;
};


export type MutationCreateMaterialTypeArgs = {
  input: MaterialInput;
};


export type MutationCreateWorkerPositionArgs = {
  input: WorkerPositionInput;
};


export type MutationDeleteApartmentHouseProjectContractArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteBridgeProjectContractArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteBrigadeArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteCustomerOrganizationArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteEmployeeArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteEngineerPositionArgs = {
  id: Scalars['Int']['input'];
};


export type MutationDeleteManufacturerArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteMaterialTypeArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteSchoolProjectContractArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteWorkScheduleUnitArgs = {
  id: Scalars['ID']['input'];
};


export type MutationDeleteWorkerPositionArgs = {
  id: Scalars['Int']['input'];
};


export type MutationUpdateCustomerOrganizationArgs = {
  id: Scalars['ID']['input'];
  input: CustomerOrganizationInput;
};


export type MutationUpdateEmployeeArgs = {
  id: Scalars['ID']['input'];
  input: EmployeeInput;
};


export type MutationUpdateEngineerPositionArgs = {
  id: Scalars['Int']['input'];
  input: EngineerPositionInput;
};


export type MutationUpdateWorkScheduleUnitArgs = {
  id: Scalars['ID']['input'];
  input: WorkScheduleUnitInput;
};


export type MutationUpdateWorkerPositionArgs = {
  id: Scalars['Int']['input'];
  input: WorkerPositionInput;
};

export type ObjectType = {
  readonly __typename?: 'ObjectType';
  readonly id: Scalars['Int']['output'];
  readonly type: Scalars['String']['output'];
};

export type Pagination = {
  readonly page: Scalars['Int']['input'];
  readonly pageSize: Scalars['Int']['input'];
};

export type ProjectContract = {
  readonly __typename?: 'ProjectContract';
  readonly dateOfCreation: Scalars['Date']['output'];
  readonly factEndDate?: Maybe<Scalars['Date']['output']>;
  readonly factStartDate?: Maybe<Scalars['Date']['output']>;
  readonly id: Scalars['ID']['output'];
  readonly objectType: Scalars['String']['output'];
  readonly planEndDate?: Maybe<Scalars['Date']['output']>;
  readonly planStartDate?: Maybe<Scalars['Date']['output']>;
  readonly projectId: Scalars['ID']['output'];
  readonly signingDate?: Maybe<Scalars['Date']['output']>;
  readonly siteId: Scalars['ID']['output'];
};

export type ProjectContractInput = {
  readonly customerId: Scalars['ID']['input'];
  readonly objectType: Scalars['String']['input'];
  readonly planEndDate: Scalars['Date']['input'];
  readonly planStartDate: Scalars['Date']['input'];
  readonly signingDate?: InputMaybe<Scalars['Date']['input']>;
  readonly siteId: Scalars['ID']['input'];
};

export type ProjectType = {
  readonly projectId: Scalars['ID']['output'];
};

export type Query = {
  readonly __typename?: 'Query';
  readonly apartmentHouseProjectContract: ApartmentHouseProjectContract;
  readonly bridgeProjectContract: BridgeProjectContract;
  readonly brigade: Brigade;
  readonly brigadeByConstructionSite: ReadonlyArray<Brigade>;
  readonly brigadeWorkSchedule: ReadonlyArray<WorkScheduleUnit>;
  readonly brigadeWorkers: ReadonlyArray<WorkerInfo>;
  readonly brigades: ReadonlyArray<Brigade>;
  readonly constructionSite: ConstructionSite;
  readonly constructionSitesBySiteManager: ConstructionSite;
  readonly customerOrganization: CustomerOrganization;
  readonly customerOrganizations: ReadonlyArray<CustomerOrganization>;
  readonly employee: EmployeeInfo;
  readonly employees: ReadonlyArray<EmployeeInfo>;
  readonly engineer: EngineerInfo;
  readonly engineerPosition: EngineerPosition;
  readonly engineerPositionByName: EngineerPosition;
  readonly engineers: ReadonlyArray<EngineerInfo>;
  readonly engineersPositions: ReadonlyArray<EngineerPosition>;
  readonly manufacturer: Manufacturer;
  readonly manufacturers: ReadonlyArray<Manufacturer>;
  readonly materialType: Material;
  readonly materialTypes: ReadonlyArray<Material>;
  readonly projectWorkSchedule: ReadonlyArray<WorkScheduleUnit>;
  readonly schoolProjectContract: SchoolProjectContract;
  readonly workMaterials: ReadonlyArray<MaterialUsage>;
  readonly workScheduleUnit: WorkScheduleUnit;
  readonly worker: WorkerInfo;
  readonly workerPosition: WorkerPosition;
  readonly workerPositionByName: EngineerPosition;
  readonly workers: ReadonlyArray<WorkerInfo>;
  readonly workersPositions: ReadonlyArray<WorkerPosition>;
};


export type QueryApartmentHouseProjectContractArgs = {
  id?: InputMaybe<Scalars['ID']['input']>;
};


export type QueryBridgeProjectContractArgs = {
  id: Scalars['ID']['input'];
};


export type QueryBrigadeArgs = {
  id: Scalars['ID']['input'];
};


export type QueryBrigadeByConstructionSiteArgs = {
  id: Scalars['ID']['input'];
  pagination: Pagination;
};


export type QueryBrigadeWorkScheduleArgs = {
  brigadeId: Scalars['ID']['input'];
  pagination: Pagination;
};


export type QueryBrigadeWorkersArgs = {
  id: Scalars['ID']['input'];
  pagination: Pagination;
};


export type QueryBrigadesArgs = {
  pagination: Pagination;
};


export type QueryConstructionSiteArgs = {
  id: Scalars['ID']['input'];
};


export type QueryConstructionSitesBySiteManagerArgs = {
  id: Scalars['ID']['input'];
};


export type QueryCustomerOrganizationArgs = {
  id: Scalars['ID']['input'];
};


export type QueryCustomerOrganizationsArgs = {
  pagination?: InputMaybe<Pagination>;
};


export type QueryEmployeeArgs = {
  id: Scalars['ID']['input'];
};


export type QueryEmployeesArgs = {
  employeeFilter?: InputMaybe<EmployeeFilter>;
  pagination: Pagination;
};


export type QueryEngineerArgs = {
  id: Scalars['ID']['input'];
};


export type QueryEngineerPositionArgs = {
  id: Scalars['Int']['input'];
};


export type QueryEngineerPositionByNameArgs = {
  name: Scalars['String']['input'];
};


export type QueryEngineersArgs = {
  engineerFilter?: InputMaybe<EngineerFilter>;
  pagination?: InputMaybe<Pagination>;
};


export type QueryEngineersPositionsArgs = {
  pagination: Pagination;
};


export type QueryManufacturerArgs = {
  id: Scalars['ID']['input'];
};


export type QueryManufacturersArgs = {
  pagination?: InputMaybe<Pagination>;
};


export type QueryMaterialTypeArgs = {
  id: Scalars['ID']['input'];
};


export type QueryMaterialTypesArgs = {
  pagination?: InputMaybe<Pagination>;
};


export type QueryProjectWorkScheduleArgs = {
  pagination: Pagination;
  projectId: Scalars['ID']['input'];
};


export type QuerySchoolProjectContractArgs = {
  id?: InputMaybe<Scalars['ID']['input']>;
};


export type QueryWorkMaterialsArgs = {
  pagination: Pagination;
  scheduleUnitID: Scalars['ID']['input'];
};


export type QueryWorkScheduleUnitArgs = {
  id: Scalars['ID']['input'];
};


export type QueryWorkerArgs = {
  id: Scalars['ID']['input'];
};


export type QueryWorkerPositionArgs = {
  id: Scalars['Int']['input'];
};


export type QueryWorkerPositionByNameArgs = {
  name: Scalars['String']['input'];
};


export type QueryWorkersArgs = {
  filter?: InputMaybe<WorkerFilter>;
  pagination: Pagination;
};


export type QueryWorkersPositionsArgs = {
  pagination: Pagination;
};

export type SchoolProject = ProjectType & {
  readonly __typename?: 'SchoolProject';
  readonly classRoomCount: Scalars['Int']['output'];
  readonly floors: Scalars['Int']['output'];
  readonly projectId: Scalars['ID']['output'];
};

export type SchoolProjectContract = {
  readonly __typename?: 'SchoolProjectContract';
  readonly dateOfCreation: Scalars['Date']['output'];
  readonly factEndDate?: Maybe<Scalars['Date']['output']>;
  readonly factStartDate?: Maybe<Scalars['Date']['output']>;
  readonly id: Scalars['ID']['output'];
  readonly objectType: Scalars['String']['output'];
  readonly planEndDate?: Maybe<Scalars['Date']['output']>;
  readonly planStartDate?: Maybe<Scalars['Date']['output']>;
  readonly project: SchoolProject;
  readonly projectId: Scalars['ID']['output'];
  readonly signingDate?: Maybe<Scalars['Date']['output']>;
  readonly siteId: Scalars['ID']['output'];
};

export type SchoolProjectInput = {
  readonly classRoomCount: Scalars['Int']['input'];
  readonly floors: Scalars['Int']['input'];
};

export type Sort = {
  readonly field: Scalars['String']['input'];
  readonly sortAsc: Scalars['Boolean']['input'];
};

export type WorkScheduleUnit = {
  readonly __typename?: 'WorkScheduleUnit';
  readonly brigadeId: Scalars['ID']['output'];
  readonly contractId: Scalars['ID']['output'];
  readonly id: Scalars['ID']['output'];
  readonly planEndDate: Scalars['Date']['output'];
  readonly planOrder: Scalars['Int']['output'];
  readonly planStartDate: Scalars['Date']['output'];
  readonly workTypeId: Scalars['ID']['output'];
};

export type WorkScheduleUnitInput = {
  readonly brigadeId: Scalars['ID']['input'];
  readonly factEndDate?: InputMaybe<Scalars['Date']['input']>;
  readonly factOrder?: InputMaybe<Scalars['Int']['input']>;
  readonly factStartDate?: InputMaybe<Scalars['Date']['input']>;
  readonly planEndDate: Scalars['Date']['input'];
  readonly planOrder: Scalars['Int']['input'];
  readonly planStartDate: Scalars['Date']['input'];
  readonly workTypeId: Scalars['ID']['input'];
};

export type WorkType = {
  readonly __typename?: 'WorkType';
  readonly id: Scalars['ID']['output'];
  readonly name?: Maybe<Scalars['String']['output']>;
};

export type WorkerFilter = {
  readonly posId?: InputMaybe<Scalars['Int']['input']>;
};

export type WorkerInfo = {
  readonly __typename?: 'WorkerInfo';
  readonly employee: EmployeeInfo;
  readonly positionInfo: WorkerPosition;
};

export type WorkerPosition = {
  readonly __typename?: 'WorkerPosition';
  readonly id: Scalars['Int']['output'];
  readonly name: Scalars['String']['output'];
};

export type WorkerPositionInput = {
  readonly name: Scalars['String']['input'];
};
