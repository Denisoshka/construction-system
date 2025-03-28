import { GraphQLResolveInfo, GraphQLScalarType, GraphQLScalarTypeConfig } from 'graphql';
export type Maybe<T> = T | null;
export type InputMaybe<T> = Maybe<T>;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
export type MakeEmpty<T extends { [key: string]: unknown }, K extends keyof T> = { [_ in K]?: never };
export type Incremental<T> = T | { [P in keyof T]?: P extends ' $fragmentName' | '__typename' ? T[P] : never };
export type RequireFields<T, K extends keyof T> = Omit<T, K> & { [P in K]-?: NonNullable<T[P]> };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: { input: string; output: string; }
  String: { input: string; output: string; }
  Boolean: { input: boolean; output: boolean; }
  Int: { input: number; output: number; }
  Float: { input: number; output: number; }
  Date: { input: any; output: any; }
};

export type ApartmentHouseProject = ProjectType & {
  __typename?: 'ApartmentHouseProject';
  floors: Scalars['Int']['output'];
  projectId: Scalars['ID']['output'];
};

export type ApartmentHouseProjectContract = {
  __typename?: 'ApartmentHouseProjectContract';
  contract: ProjectContract;
  project: ApartmentHouseProject;
};

export type ApartmentHouseProjectInput = {
  floors: Scalars['Int']['input'];
};

export type BridgeProject = ProjectType & {
  __typename?: 'BridgeProject';
  projectId: Scalars['ID']['output'];
  span: Scalars['Int']['output'];
  trafficLanesNumber: Scalars['Int']['output'];
  width: Scalars['Int']['output'];
};

export type BridgeProjectContract = {
  __typename?: 'BridgeProjectContract';
  contract: ProjectContract;
  project: BridgeProject;
};

export type BridgeProjectInput = {
  span: Scalars['Int']['input'];
  trafficLanesNumber: Scalars['Int']['input'];
  width: Scalars['Int']['input'];
};

export type Brigade = {
  __typename?: 'Brigade';
  foreman: WorkerInfo;
  id: Scalars['ID']['output'];
  siteInfo: ConstructionSite;
};

export type BrigadeInput = {
  foremanId: Scalars['ID']['input'];
  siteTeamID: Scalars['ID']['input'];
};

export type ConstructionProject = {
  __typename?: 'ConstructionProject';
  id: Scalars['ID']['output'];
  siteID: Scalars['ID']['output'];
};

export type ConstructionSite = {
  __typename?: 'ConstructionSite';
  address: Scalars['String']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
  phone: Scalars['String']['output'];
  siteManager?: Maybe<EngineerInfo>;
};

export type ConstructionSiteInput = {
  address: Scalars['String']['input'];
  managementId?: InputMaybe<Scalars['ID']['input']>;
  name: Scalars['String']['input'];
  phone: Scalars['String']['input'];
  siteManagerId?: InputMaybe<Scalars['ID']['input']>;
};

export type ContractFilter = {
  projectType?: InputMaybe<Scalars['String']['input']>;
};

export type CustomerOrganization = {
  __typename?: 'CustomerOrganization';
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
};

export type CustomerOrganizationInput = {
  name?: InputMaybe<Scalars['String']['input']>;
};

export type Employee = {
  employmentDate: Scalars['Date']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
  patronymic?: Maybe<Scalars['String']['output']>;
  surname: Scalars['String']['output'];
  systemId: Scalars['String']['output'];
};

export type EmployeeFilter = {
  post?: InputMaybe<Scalars['String']['input']>;
};

export type EmployeeInfo = Employee & {
  __typename?: 'EmployeeInfo';
  employmentDate: Scalars['Date']['output'];
  id: Scalars['ID']['output'];
  name: Scalars['String']['output'];
  patronymic?: Maybe<Scalars['String']['output']>;
  post: JobPost;
  surname: Scalars['String']['output'];
  systemId: Scalars['String']['output'];
};

export type EmployeeInput = {
  name: Scalars['String']['input'];
  patronymic?: InputMaybe<Scalars['String']['input']>;
  surname: Scalars['String']['input'];
  systemId: Scalars['String']['input'];
};

export type EngineerFilter = {
  posId?: InputMaybe<Scalars['Int']['input']>;
};

export type EngineerInfo = {
  __typename?: 'EngineerInfo';
  employee: EmployeeInfo;
  positionInfo: EngineerPosition;
};

export type EngineerPosition = {
  __typename?: 'EngineerPosition';
  id: Scalars['Int']['output'];
  name: Scalars['String']['output'];
};

export type EngineerPositionInput = {
  name: Scalars['String']['input'];
};

export enum JobPost {
  Engineer = 'ENGINEER',
  Unknown = 'UNKNOWN',
  Worker = 'WORKER'
}

export type Manufacturer = {
  __typename?: 'Manufacturer';
  id: Scalars['ID']['output'];
  manufacturer: Scalars['String']['output'];
};

export type ManufacturerInput = {
  manufacturer: Scalars['String']['input'];
};

export type Material = {
  __typename?: 'Material';
  cost: Scalars['Int']['output'];
  id: Scalars['ID']['output'];
  manufacturerId: Scalars['ID']['output'];
  name: Scalars['String']['output'];
};

export type MaterialInput = {
  cost: Scalars['Int']['input'];
  manufacturer: Scalars['ID']['input'];
  name: Scalars['String']['input'];
};

export type MaterialUsage = {
  __typename?: 'MaterialUsage';
  factQuantity: Scalars['Int']['output'];
  materialID: Scalars['ID']['output'];
  planQuantity: Scalars['Int']['output'];
  workUnitId: Scalars['ID']['output'];
};

export type MaterialUsageInput = {
  factQuantity: Scalars['Int']['input'];
  materialID: Scalars['ID']['input'];
  planQuantity: Scalars['Int']['input'];
  workUnitId: Scalars['ID']['input'];
};

export type Mutation = {
  __typename?: 'Mutation';
  addApartmentHouseProjectContract: ProjectContract;
  addBridgeProjectContract: ProjectContract;
  addSchoolProjectContract: ProjectContract;
  addWorkScheduleUnit: WorkScheduleUnit;
  addWorkerInBrigade?: Maybe<Scalars['Boolean']['output']>;
  createBrigade: Brigade;
  createCustomerOrganization: CustomerOrganization;
  createEmployee: EmployeeInfo;
  createEngineerPosition: EngineerPosition;
  createManufacturer: Manufacturer;
  createMaterialType: Material;
  createWorkerPosition: WorkerPosition;
  deleteApartmentHouseProjectContract: Scalars['Boolean']['output'];
  deleteBridgeProjectContract: Scalars['Boolean']['output'];
  deleteBrigade: Scalars['Boolean']['output'];
  deleteCustomerOrganization: Scalars['Boolean']['output'];
  deleteEmployee: Scalars['Boolean']['output'];
  deleteEngineerPosition: Scalars['Boolean']['output'];
  deleteManufacturer: Scalars['Boolean']['output'];
  deleteMaterialType: Scalars['Boolean']['output'];
  deleteSchoolProjectContract: Scalars['Boolean']['output'];
  deleteWorkScheduleUnit: Scalars['Boolean']['output'];
  deleteWorkerPosition: Scalars['Boolean']['output'];
  updateCustomerOrganization: CustomerOrganization;
  updateEmployee: EmployeeInfo;
  updateEngineerPosition: EngineerPosition;
  updateWorkScheduleUnit: WorkScheduleUnit;
  updateWorkerPosition: EngineerPosition;
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
  __typename?: 'ObjectType';
  id: Scalars['Int']['output'];
  type: Scalars['String']['output'];
};

export type Pagination = {
  page: Scalars['Int']['input'];
  pageSize: Scalars['Int']['input'];
};

export type ProjectContract = {
  __typename?: 'ProjectContract';
  dateOfCreation: Scalars['Date']['output'];
  factEndDate?: Maybe<Scalars['Date']['output']>;
  factStartDate?: Maybe<Scalars['Date']['output']>;
  id: Scalars['ID']['output'];
  objectType: Scalars['String']['output'];
  planEndDate?: Maybe<Scalars['Date']['output']>;
  planStartDate?: Maybe<Scalars['Date']['output']>;
  projectId: Scalars['ID']['output'];
  signingDate?: Maybe<Scalars['Date']['output']>;
  siteId: Scalars['ID']['output'];
};

export type ProjectContractInput = {
  customerId: Scalars['ID']['input'];
  objectType: Scalars['String']['input'];
  planEndDate: Scalars['Date']['input'];
  planStartDate: Scalars['Date']['input'];
  signingDate?: InputMaybe<Scalars['Date']['input']>;
  siteId: Scalars['ID']['input'];
};

export type ProjectType = {
  projectId: Scalars['ID']['output'];
};

export type Query = {
  __typename?: 'Query';
  apartmentHouseProjectContract: ApartmentHouseProjectContract;
  bridgeProjectContract: BridgeProjectContract;
  brigade: Brigade;
  brigadeByConstructionSite: Array<Brigade>;
  brigadeWorkSchedule: Array<WorkScheduleUnit>;
  brigadeWorkers: Array<WorkerInfo>;
  brigades: Array<Brigade>;
  constructionSite: ConstructionSite;
  constructionSitesBySiteManager: ConstructionSite;
  customerOrganization: CustomerOrganization;
  customerOrganizations: Array<CustomerOrganization>;
  employee: EmployeeInfo;
  employees: Array<EmployeeInfo>;
  engineer: EngineerInfo;
  engineerPosition: EngineerPosition;
  engineerPositionByName: EngineerPosition;
  engineers: Array<EngineerInfo>;
  engineersPositions: Array<EngineerPosition>;
  manufacturer: Manufacturer;
  manufacturers: Array<Manufacturer>;
  materialType: Material;
  materialTypes: Array<Material>;
  projectWorkSchedule: Array<WorkScheduleUnit>;
  schoolProjectContract: SchoolProjectContract;
  workMaterials: Array<MaterialUsage>;
  workScheduleUnit: WorkScheduleUnit;
  worker: WorkerInfo;
  workerPosition: WorkerPosition;
  workerPositionByName: EngineerPosition;
  workers: Array<WorkerInfo>;
  workersPositions: Array<WorkerPosition>;
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
  __typename?: 'SchoolProject';
  classRoomCount: Scalars['Int']['output'];
  floors: Scalars['Int']['output'];
  projectId: Scalars['ID']['output'];
};

export type SchoolProjectContract = {
  __typename?: 'SchoolProjectContract';
  dateOfCreation: Scalars['Date']['output'];
  factEndDate?: Maybe<Scalars['Date']['output']>;
  factStartDate?: Maybe<Scalars['Date']['output']>;
  id: Scalars['ID']['output'];
  objectType: Scalars['String']['output'];
  planEndDate?: Maybe<Scalars['Date']['output']>;
  planStartDate?: Maybe<Scalars['Date']['output']>;
  project: SchoolProject;
  projectId: Scalars['ID']['output'];
  signingDate?: Maybe<Scalars['Date']['output']>;
  siteId: Scalars['ID']['output'];
};

export type SchoolProjectInput = {
  classRoomCount: Scalars['Int']['input'];
  floors: Scalars['Int']['input'];
};

export type Sort = {
  field: Scalars['String']['input'];
  sortAsc: Scalars['Boolean']['input'];
};

export type WorkScheduleUnit = {
  __typename?: 'WorkScheduleUnit';
  brigadeId: Scalars['ID']['output'];
  contractId: Scalars['ID']['output'];
  id: Scalars['ID']['output'];
  planEndDate: Scalars['Date']['output'];
  planOrder: Scalars['Int']['output'];
  planStartDate: Scalars['Date']['output'];
  workTypeId: Scalars['ID']['output'];
};

export type WorkScheduleUnitInput = {
  brigadeId: Scalars['ID']['input'];
  factEndDate?: InputMaybe<Scalars['Date']['input']>;
  factOrder?: InputMaybe<Scalars['Int']['input']>;
  factStartDate?: InputMaybe<Scalars['Date']['input']>;
  planEndDate: Scalars['Date']['input'];
  planOrder: Scalars['Int']['input'];
  planStartDate: Scalars['Date']['input'];
  workTypeId: Scalars['ID']['input'];
};

export type WorkType = {
  __typename?: 'WorkType';
  id: Scalars['ID']['output'];
  name?: Maybe<Scalars['String']['output']>;
};

export type WorkerFilter = {
  posId?: InputMaybe<Scalars['Int']['input']>;
};

export type WorkerInfo = {
  __typename?: 'WorkerInfo';
  employee: EmployeeInfo;
  positionInfo: WorkerPosition;
};

export type WorkerPosition = {
  __typename?: 'WorkerPosition';
  id: Scalars['Int']['output'];
  name: Scalars['String']['output'];
};

export type WorkerPositionInput = {
  name: Scalars['String']['input'];
};



export type ResolverTypeWrapper<T> = Promise<T> | T;


export type ResolverWithResolve<TResult, TParent, TContext, TArgs> = {
  resolve: ResolverFn<TResult, TParent, TContext, TArgs>;
};
export type Resolver<TResult, TParent = {}, TContext = {}, TArgs = {}> = ResolverFn<TResult, TParent, TContext, TArgs> | ResolverWithResolve<TResult, TParent, TContext, TArgs>;

export type ResolverFn<TResult, TParent, TContext, TArgs> = (
  parent: TParent,
  args: TArgs,
  context: TContext,
  info: GraphQLResolveInfo
) => Promise<TResult> | TResult;

export type SubscriptionSubscribeFn<TResult, TParent, TContext, TArgs> = (
  parent: TParent,
  args: TArgs,
  context: TContext,
  info: GraphQLResolveInfo
) => AsyncIterable<TResult> | Promise<AsyncIterable<TResult>>;

export type SubscriptionResolveFn<TResult, TParent, TContext, TArgs> = (
  parent: TParent,
  args: TArgs,
  context: TContext,
  info: GraphQLResolveInfo
) => TResult | Promise<TResult>;

export interface SubscriptionSubscriberObject<TResult, TKey extends string, TParent, TContext, TArgs> {
  subscribe: SubscriptionSubscribeFn<{ [key in TKey]: TResult }, TParent, TContext, TArgs>;
  resolve?: SubscriptionResolveFn<TResult, { [key in TKey]: TResult }, TContext, TArgs>;
}

export interface SubscriptionResolverObject<TResult, TParent, TContext, TArgs> {
  subscribe: SubscriptionSubscribeFn<any, TParent, TContext, TArgs>;
  resolve: SubscriptionResolveFn<TResult, any, TContext, TArgs>;
}

export type SubscriptionObject<TResult, TKey extends string, TParent, TContext, TArgs> =
  | SubscriptionSubscriberObject<TResult, TKey, TParent, TContext, TArgs>
  | SubscriptionResolverObject<TResult, TParent, TContext, TArgs>;

export type SubscriptionResolver<TResult, TKey extends string, TParent = {}, TContext = {}, TArgs = {}> =
  | ((...args: any[]) => SubscriptionObject<TResult, TKey, TParent, TContext, TArgs>)
  | SubscriptionObject<TResult, TKey, TParent, TContext, TArgs>;

export type TypeResolveFn<TTypes, TParent = {}, TContext = {}> = (
  parent: TParent,
  context: TContext,
  info: GraphQLResolveInfo
) => Maybe<TTypes> | Promise<Maybe<TTypes>>;

export type IsTypeOfResolverFn<T = {}, TContext = {}> = (obj: T, context: TContext, info: GraphQLResolveInfo) => boolean | Promise<boolean>;

export type NextResolverFn<T> = () => Promise<T>;

export type DirectiveResolverFn<TResult = {}, TParent = {}, TContext = {}, TArgs = {}> = (
  next: NextResolverFn<TResult>,
  parent: TParent,
  args: TArgs,
  context: TContext,
  info: GraphQLResolveInfo
) => TResult | Promise<TResult>;


/** Mapping of interface types */
export type ResolversInterfaceTypes<_RefType extends Record<string, unknown>> = {
  Employee: ( EmployeeInfo );
  ProjectType: ( ApartmentHouseProject ) | ( BridgeProject ) | ( SchoolProject );
};

/** Mapping between all available schema types and the resolvers types */
export type ResolversTypes = {
  ApartmentHouseProject: ResolverTypeWrapper<ApartmentHouseProject>;
  ApartmentHouseProjectContract: ResolverTypeWrapper<ApartmentHouseProjectContract>;
  ApartmentHouseProjectInput: ApartmentHouseProjectInput;
  Boolean: ResolverTypeWrapper<Scalars['Boolean']['output']>;
  BridgeProject: ResolverTypeWrapper<BridgeProject>;
  BridgeProjectContract: ResolverTypeWrapper<BridgeProjectContract>;
  BridgeProjectInput: BridgeProjectInput;
  Brigade: ResolverTypeWrapper<Brigade>;
  BrigadeInput: BrigadeInput;
  ConstructionProject: ResolverTypeWrapper<ConstructionProject>;
  ConstructionSite: ResolverTypeWrapper<ConstructionSite>;
  ConstructionSiteInput: ConstructionSiteInput;
  ContractFilter: ContractFilter;
  CustomerOrganization: ResolverTypeWrapper<CustomerOrganization>;
  CustomerOrganizationInput: CustomerOrganizationInput;
  Date: ResolverTypeWrapper<Scalars['Date']['output']>;
  Employee: ResolverTypeWrapper<ResolversInterfaceTypes<ResolversTypes>['Employee']>;
  EmployeeFilter: EmployeeFilter;
  EmployeeInfo: ResolverTypeWrapper<EmployeeInfo>;
  EmployeeInput: EmployeeInput;
  EngineerFilter: EngineerFilter;
  EngineerInfo: ResolverTypeWrapper<EngineerInfo>;
  EngineerPosition: ResolverTypeWrapper<EngineerPosition>;
  EngineerPositionInput: EngineerPositionInput;
  ID: ResolverTypeWrapper<Scalars['ID']['output']>;
  Int: ResolverTypeWrapper<Scalars['Int']['output']>;
  JobPost: JobPost;
  Manufacturer: ResolverTypeWrapper<Manufacturer>;
  ManufacturerInput: ManufacturerInput;
  Material: ResolverTypeWrapper<Material>;
  MaterialInput: MaterialInput;
  MaterialUsage: ResolverTypeWrapper<MaterialUsage>;
  MaterialUsageInput: MaterialUsageInput;
  Mutation: ResolverTypeWrapper<{}>;
  ObjectType: ResolverTypeWrapper<ObjectType>;
  Pagination: Pagination;
  ProjectContract: ResolverTypeWrapper<ProjectContract>;
  ProjectContractInput: ProjectContractInput;
  ProjectType: ResolverTypeWrapper<ResolversInterfaceTypes<ResolversTypes>['ProjectType']>;
  Query: ResolverTypeWrapper<{}>;
  SchoolProject: ResolverTypeWrapper<SchoolProject>;
  SchoolProjectContract: ResolverTypeWrapper<SchoolProjectContract>;
  SchoolProjectInput: SchoolProjectInput;
  Sort: Sort;
  String: ResolverTypeWrapper<Scalars['String']['output']>;
  WorkScheduleUnit: ResolverTypeWrapper<WorkScheduleUnit>;
  WorkScheduleUnitInput: WorkScheduleUnitInput;
  WorkType: ResolverTypeWrapper<WorkType>;
  WorkerFilter: WorkerFilter;
  WorkerInfo: ResolverTypeWrapper<WorkerInfo>;
  WorkerPosition: ResolverTypeWrapper<WorkerPosition>;
  WorkerPositionInput: WorkerPositionInput;
};

/** Mapping between all available schema types and the resolvers parents */
export type ResolversParentTypes = {
  ApartmentHouseProject: ApartmentHouseProject;
  ApartmentHouseProjectContract: ApartmentHouseProjectContract;
  ApartmentHouseProjectInput: ApartmentHouseProjectInput;
  Boolean: Scalars['Boolean']['output'];
  BridgeProject: BridgeProject;
  BridgeProjectContract: BridgeProjectContract;
  BridgeProjectInput: BridgeProjectInput;
  Brigade: Brigade;
  BrigadeInput: BrigadeInput;
  ConstructionProject: ConstructionProject;
  ConstructionSite: ConstructionSite;
  ConstructionSiteInput: ConstructionSiteInput;
  ContractFilter: ContractFilter;
  CustomerOrganization: CustomerOrganization;
  CustomerOrganizationInput: CustomerOrganizationInput;
  Date: Scalars['Date']['output'];
  Employee: ResolversInterfaceTypes<ResolversParentTypes>['Employee'];
  EmployeeFilter: EmployeeFilter;
  EmployeeInfo: EmployeeInfo;
  EmployeeInput: EmployeeInput;
  EngineerFilter: EngineerFilter;
  EngineerInfo: EngineerInfo;
  EngineerPosition: EngineerPosition;
  EngineerPositionInput: EngineerPositionInput;
  ID: Scalars['ID']['output'];
  Int: Scalars['Int']['output'];
  Manufacturer: Manufacturer;
  ManufacturerInput: ManufacturerInput;
  Material: Material;
  MaterialInput: MaterialInput;
  MaterialUsage: MaterialUsage;
  MaterialUsageInput: MaterialUsageInput;
  Mutation: {};
  ObjectType: ObjectType;
  Pagination: Pagination;
  ProjectContract: ProjectContract;
  ProjectContractInput: ProjectContractInput;
  ProjectType: ResolversInterfaceTypes<ResolversParentTypes>['ProjectType'];
  Query: {};
  SchoolProject: SchoolProject;
  SchoolProjectContract: SchoolProjectContract;
  SchoolProjectInput: SchoolProjectInput;
  Sort: Sort;
  String: Scalars['String']['output'];
  WorkScheduleUnit: WorkScheduleUnit;
  WorkScheduleUnitInput: WorkScheduleUnitInput;
  WorkType: WorkType;
  WorkerFilter: WorkerFilter;
  WorkerInfo: WorkerInfo;
  WorkerPosition: WorkerPosition;
  WorkerPositionInput: WorkerPositionInput;
};

export type ApartmentHouseProjectResolvers<ContextType = any, ParentType extends ResolversParentTypes['ApartmentHouseProject'] = ResolversParentTypes['ApartmentHouseProject']> = {
  floors?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  projectId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type ApartmentHouseProjectContractResolvers<ContextType = any, ParentType extends ResolversParentTypes['ApartmentHouseProjectContract'] = ResolversParentTypes['ApartmentHouseProjectContract']> = {
  contract?: Resolver<ResolversTypes['ProjectContract'], ParentType, ContextType>;
  project?: Resolver<ResolversTypes['ApartmentHouseProject'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type BridgeProjectResolvers<ContextType = any, ParentType extends ResolversParentTypes['BridgeProject'] = ResolversParentTypes['BridgeProject']> = {
  projectId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  span?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  trafficLanesNumber?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  width?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type BridgeProjectContractResolvers<ContextType = any, ParentType extends ResolversParentTypes['BridgeProjectContract'] = ResolversParentTypes['BridgeProjectContract']> = {
  contract?: Resolver<ResolversTypes['ProjectContract'], ParentType, ContextType>;
  project?: Resolver<ResolversTypes['BridgeProject'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type BrigadeResolvers<ContextType = any, ParentType extends ResolversParentTypes['Brigade'] = ResolversParentTypes['Brigade']> = {
  foreman?: Resolver<ResolversTypes['WorkerInfo'], ParentType, ContextType>;
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  siteInfo?: Resolver<ResolversTypes['ConstructionSite'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type ConstructionProjectResolvers<ContextType = any, ParentType extends ResolversParentTypes['ConstructionProject'] = ResolversParentTypes['ConstructionProject']> = {
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  siteID?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type ConstructionSiteResolvers<ContextType = any, ParentType extends ResolversParentTypes['ConstructionSite'] = ResolversParentTypes['ConstructionSite']> = {
  address?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  name?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  phone?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  siteManager?: Resolver<Maybe<ResolversTypes['EngineerInfo']>, ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type CustomerOrganizationResolvers<ContextType = any, ParentType extends ResolversParentTypes['CustomerOrganization'] = ResolversParentTypes['CustomerOrganization']> = {
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  name?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export interface DateScalarConfig extends GraphQLScalarTypeConfig<ResolversTypes['Date'], any> {
  name: 'Date';
}

export type EmployeeResolvers<ContextType = any, ParentType extends ResolversParentTypes['Employee'] = ResolversParentTypes['Employee']> = {
  __resolveType: TypeResolveFn<'EmployeeInfo', ParentType, ContextType>;
  employmentDate?: Resolver<ResolversTypes['Date'], ParentType, ContextType>;
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  name?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  patronymic?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  surname?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  systemId?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
};

export type EmployeeInfoResolvers<ContextType = any, ParentType extends ResolversParentTypes['EmployeeInfo'] = ResolversParentTypes['EmployeeInfo']> = {
  employmentDate?: Resolver<ResolversTypes['Date'], ParentType, ContextType>;
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  name?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  patronymic?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  post?: Resolver<ResolversTypes['JobPost'], ParentType, ContextType>;
  surname?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  systemId?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type EngineerInfoResolvers<ContextType = any, ParentType extends ResolversParentTypes['EngineerInfo'] = ResolversParentTypes['EngineerInfo']> = {
  employee?: Resolver<ResolversTypes['EmployeeInfo'], ParentType, ContextType>;
  positionInfo?: Resolver<ResolversTypes['EngineerPosition'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type EngineerPositionResolvers<ContextType = any, ParentType extends ResolversParentTypes['EngineerPosition'] = ResolversParentTypes['EngineerPosition']> = {
  id?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  name?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type ManufacturerResolvers<ContextType = any, ParentType extends ResolversParentTypes['Manufacturer'] = ResolversParentTypes['Manufacturer']> = {
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  manufacturer?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type MaterialResolvers<ContextType = any, ParentType extends ResolversParentTypes['Material'] = ResolversParentTypes['Material']> = {
  cost?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  manufacturerId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  name?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type MaterialUsageResolvers<ContextType = any, ParentType extends ResolversParentTypes['MaterialUsage'] = ResolversParentTypes['MaterialUsage']> = {
  factQuantity?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  materialID?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  planQuantity?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  workUnitId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type MutationResolvers<ContextType = any, ParentType extends ResolversParentTypes['Mutation'] = ResolversParentTypes['Mutation']> = {
  addApartmentHouseProjectContract?: Resolver<ResolversTypes['ProjectContract'], ParentType, ContextType, RequireFields<MutationAddApartmentHouseProjectContractArgs, 'input'>>;
  addBridgeProjectContract?: Resolver<ResolversTypes['ProjectContract'], ParentType, ContextType, RequireFields<MutationAddBridgeProjectContractArgs, 'input'>>;
  addSchoolProjectContract?: Resolver<ResolversTypes['ProjectContract'], ParentType, ContextType, RequireFields<MutationAddSchoolProjectContractArgs, 'input'>>;
  addWorkScheduleUnit?: Resolver<ResolversTypes['WorkScheduleUnit'], ParentType, ContextType, RequireFields<MutationAddWorkScheduleUnitArgs, 'input'>>;
  addWorkerInBrigade?: Resolver<Maybe<ResolversTypes['Boolean']>, ParentType, ContextType, RequireFields<MutationAddWorkerInBrigadeArgs, 'BrigadeID' | 'workerId'>>;
  createBrigade?: Resolver<ResolversTypes['Brigade'], ParentType, ContextType, RequireFields<MutationCreateBrigadeArgs, 'input'>>;
  createCustomerOrganization?: Resolver<ResolversTypes['CustomerOrganization'], ParentType, ContextType, RequireFields<MutationCreateCustomerOrganizationArgs, 'input'>>;
  createEmployee?: Resolver<ResolversTypes['EmployeeInfo'], ParentType, ContextType, RequireFields<MutationCreateEmployeeArgs, 'input'>>;
  createEngineerPosition?: Resolver<ResolversTypes['EngineerPosition'], ParentType, ContextType, RequireFields<MutationCreateEngineerPositionArgs, 'input'>>;
  createManufacturer?: Resolver<ResolversTypes['Manufacturer'], ParentType, ContextType, RequireFields<MutationCreateManufacturerArgs, 'input'>>;
  createMaterialType?: Resolver<ResolversTypes['Material'], ParentType, ContextType, RequireFields<MutationCreateMaterialTypeArgs, 'input'>>;
  createWorkerPosition?: Resolver<ResolversTypes['WorkerPosition'], ParentType, ContextType, RequireFields<MutationCreateWorkerPositionArgs, 'input'>>;
  deleteApartmentHouseProjectContract?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteApartmentHouseProjectContractArgs, 'id'>>;
  deleteBridgeProjectContract?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteBridgeProjectContractArgs, 'id'>>;
  deleteBrigade?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteBrigadeArgs, 'id'>>;
  deleteCustomerOrganization?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteCustomerOrganizationArgs, 'id'>>;
  deleteEmployee?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteEmployeeArgs, 'id'>>;
  deleteEngineerPosition?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteEngineerPositionArgs, 'id'>>;
  deleteManufacturer?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteManufacturerArgs, 'id'>>;
  deleteMaterialType?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteMaterialTypeArgs, 'id'>>;
  deleteSchoolProjectContract?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteSchoolProjectContractArgs, 'id'>>;
  deleteWorkScheduleUnit?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteWorkScheduleUnitArgs, 'id'>>;
  deleteWorkerPosition?: Resolver<ResolversTypes['Boolean'], ParentType, ContextType, RequireFields<MutationDeleteWorkerPositionArgs, 'id'>>;
  updateCustomerOrganization?: Resolver<ResolversTypes['CustomerOrganization'], ParentType, ContextType, RequireFields<MutationUpdateCustomerOrganizationArgs, 'id' | 'input'>>;
  updateEmployee?: Resolver<ResolversTypes['EmployeeInfo'], ParentType, ContextType, RequireFields<MutationUpdateEmployeeArgs, 'id' | 'input'>>;
  updateEngineerPosition?: Resolver<ResolversTypes['EngineerPosition'], ParentType, ContextType, RequireFields<MutationUpdateEngineerPositionArgs, 'id' | 'input'>>;
  updateWorkScheduleUnit?: Resolver<ResolversTypes['WorkScheduleUnit'], ParentType, ContextType, RequireFields<MutationUpdateWorkScheduleUnitArgs, 'id' | 'input'>>;
  updateWorkerPosition?: Resolver<ResolversTypes['EngineerPosition'], ParentType, ContextType, RequireFields<MutationUpdateWorkerPositionArgs, 'id' | 'input'>>;
};

export type ObjectTypeResolvers<ContextType = any, ParentType extends ResolversParentTypes['ObjectType'] = ResolversParentTypes['ObjectType']> = {
  id?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  type?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type ProjectContractResolvers<ContextType = any, ParentType extends ResolversParentTypes['ProjectContract'] = ResolversParentTypes['ProjectContract']> = {
  dateOfCreation?: Resolver<ResolversTypes['Date'], ParentType, ContextType>;
  factEndDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  factStartDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  objectType?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  planEndDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  planStartDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  projectId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  signingDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  siteId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type ProjectTypeResolvers<ContextType = any, ParentType extends ResolversParentTypes['ProjectType'] = ResolversParentTypes['ProjectType']> = {
  __resolveType: TypeResolveFn<'ApartmentHouseProject' | 'BridgeProject' | 'SchoolProject', ParentType, ContextType>;
  projectId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
};

export type QueryResolvers<ContextType = any, ParentType extends ResolversParentTypes['Query'] = ResolversParentTypes['Query']> = {
  apartmentHouseProjectContract?: Resolver<ResolversTypes['ApartmentHouseProjectContract'], ParentType, ContextType, Partial<QueryApartmentHouseProjectContractArgs>>;
  bridgeProjectContract?: Resolver<ResolversTypes['BridgeProjectContract'], ParentType, ContextType, RequireFields<QueryBridgeProjectContractArgs, 'id'>>;
  brigade?: Resolver<ResolversTypes['Brigade'], ParentType, ContextType, RequireFields<QueryBrigadeArgs, 'id'>>;
  brigadeByConstructionSite?: Resolver<Array<ResolversTypes['Brigade']>, ParentType, ContextType, RequireFields<QueryBrigadeByConstructionSiteArgs, 'id' | 'pagination'>>;
  brigadeWorkSchedule?: Resolver<Array<ResolversTypes['WorkScheduleUnit']>, ParentType, ContextType, RequireFields<QueryBrigadeWorkScheduleArgs, 'brigadeId' | 'pagination'>>;
  brigadeWorkers?: Resolver<Array<ResolversTypes['WorkerInfo']>, ParentType, ContextType, RequireFields<QueryBrigadeWorkersArgs, 'id' | 'pagination'>>;
  brigades?: Resolver<Array<ResolversTypes['Brigade']>, ParentType, ContextType, RequireFields<QueryBrigadesArgs, 'pagination'>>;
  constructionSite?: Resolver<ResolversTypes['ConstructionSite'], ParentType, ContextType, RequireFields<QueryConstructionSiteArgs, 'id'>>;
  constructionSitesBySiteManager?: Resolver<ResolversTypes['ConstructionSite'], ParentType, ContextType, RequireFields<QueryConstructionSitesBySiteManagerArgs, 'id'>>;
  customerOrganization?: Resolver<ResolversTypes['CustomerOrganization'], ParentType, ContextType, RequireFields<QueryCustomerOrganizationArgs, 'id'>>;
  customerOrganizations?: Resolver<Array<ResolversTypes['CustomerOrganization']>, ParentType, ContextType, Partial<QueryCustomerOrganizationsArgs>>;
  employee?: Resolver<ResolversTypes['EmployeeInfo'], ParentType, ContextType, RequireFields<QueryEmployeeArgs, 'id'>>;
  employees?: Resolver<Array<ResolversTypes['EmployeeInfo']>, ParentType, ContextType, RequireFields<QueryEmployeesArgs, 'pagination'>>;
  engineer?: Resolver<ResolversTypes['EngineerInfo'], ParentType, ContextType, RequireFields<QueryEngineerArgs, 'id'>>;
  engineerPosition?: Resolver<ResolversTypes['EngineerPosition'], ParentType, ContextType, RequireFields<QueryEngineerPositionArgs, 'id'>>;
  engineerPositionByName?: Resolver<ResolversTypes['EngineerPosition'], ParentType, ContextType, RequireFields<QueryEngineerPositionByNameArgs, 'name'>>;
  engineers?: Resolver<Array<ResolversTypes['EngineerInfo']>, ParentType, ContextType, Partial<QueryEngineersArgs>>;
  engineersPositions?: Resolver<Array<ResolversTypes['EngineerPosition']>, ParentType, ContextType, RequireFields<QueryEngineersPositionsArgs, 'pagination'>>;
  manufacturer?: Resolver<ResolversTypes['Manufacturer'], ParentType, ContextType, RequireFields<QueryManufacturerArgs, 'id'>>;
  manufacturers?: Resolver<Array<ResolversTypes['Manufacturer']>, ParentType, ContextType, Partial<QueryManufacturersArgs>>;
  materialType?: Resolver<ResolversTypes['Material'], ParentType, ContextType, RequireFields<QueryMaterialTypeArgs, 'id'>>;
  materialTypes?: Resolver<Array<ResolversTypes['Material']>, ParentType, ContextType, Partial<QueryMaterialTypesArgs>>;
  projectWorkSchedule?: Resolver<Array<ResolversTypes['WorkScheduleUnit']>, ParentType, ContextType, RequireFields<QueryProjectWorkScheduleArgs, 'pagination' | 'projectId'>>;
  schoolProjectContract?: Resolver<ResolversTypes['SchoolProjectContract'], ParentType, ContextType, Partial<QuerySchoolProjectContractArgs>>;
  workMaterials?: Resolver<Array<ResolversTypes['MaterialUsage']>, ParentType, ContextType, RequireFields<QueryWorkMaterialsArgs, 'pagination' | 'scheduleUnitID'>>;
  workScheduleUnit?: Resolver<ResolversTypes['WorkScheduleUnit'], ParentType, ContextType, RequireFields<QueryWorkScheduleUnitArgs, 'id'>>;
  worker?: Resolver<ResolversTypes['WorkerInfo'], ParentType, ContextType, RequireFields<QueryWorkerArgs, 'id'>>;
  workerPosition?: Resolver<ResolversTypes['WorkerPosition'], ParentType, ContextType, RequireFields<QueryWorkerPositionArgs, 'id'>>;
  workerPositionByName?: Resolver<ResolversTypes['EngineerPosition'], ParentType, ContextType, RequireFields<QueryWorkerPositionByNameArgs, 'name'>>;
  workers?: Resolver<Array<ResolversTypes['WorkerInfo']>, ParentType, ContextType, RequireFields<QueryWorkersArgs, 'pagination'>>;
  workersPositions?: Resolver<Array<ResolversTypes['WorkerPosition']>, ParentType, ContextType, RequireFields<QueryWorkersPositionsArgs, 'pagination'>>;
};

export type SchoolProjectResolvers<ContextType = any, ParentType extends ResolversParentTypes['SchoolProject'] = ResolversParentTypes['SchoolProject']> = {
  classRoomCount?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  floors?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  projectId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type SchoolProjectContractResolvers<ContextType = any, ParentType extends ResolversParentTypes['SchoolProjectContract'] = ResolversParentTypes['SchoolProjectContract']> = {
  dateOfCreation?: Resolver<ResolversTypes['Date'], ParentType, ContextType>;
  factEndDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  factStartDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  objectType?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  planEndDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  planStartDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  project?: Resolver<ResolversTypes['SchoolProject'], ParentType, ContextType>;
  projectId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  signingDate?: Resolver<Maybe<ResolversTypes['Date']>, ParentType, ContextType>;
  siteId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type WorkScheduleUnitResolvers<ContextType = any, ParentType extends ResolversParentTypes['WorkScheduleUnit'] = ResolversParentTypes['WorkScheduleUnit']> = {
  brigadeId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  contractId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  planEndDate?: Resolver<ResolversTypes['Date'], ParentType, ContextType>;
  planOrder?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  planStartDate?: Resolver<ResolversTypes['Date'], ParentType, ContextType>;
  workTypeId?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type WorkTypeResolvers<ContextType = any, ParentType extends ResolversParentTypes['WorkType'] = ResolversParentTypes['WorkType']> = {
  id?: Resolver<ResolversTypes['ID'], ParentType, ContextType>;
  name?: Resolver<Maybe<ResolversTypes['String']>, ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type WorkerInfoResolvers<ContextType = any, ParentType extends ResolversParentTypes['WorkerInfo'] = ResolversParentTypes['WorkerInfo']> = {
  employee?: Resolver<ResolversTypes['EmployeeInfo'], ParentType, ContextType>;
  positionInfo?: Resolver<ResolversTypes['WorkerPosition'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type WorkerPositionResolvers<ContextType = any, ParentType extends ResolversParentTypes['WorkerPosition'] = ResolversParentTypes['WorkerPosition']> = {
  id?: Resolver<ResolversTypes['Int'], ParentType, ContextType>;
  name?: Resolver<ResolversTypes['String'], ParentType, ContextType>;
  __isTypeOf?: IsTypeOfResolverFn<ParentType, ContextType>;
};

export type Resolvers<ContextType = any> = {
  ApartmentHouseProject?: ApartmentHouseProjectResolvers<ContextType>;
  ApartmentHouseProjectContract?: ApartmentHouseProjectContractResolvers<ContextType>;
  BridgeProject?: BridgeProjectResolvers<ContextType>;
  BridgeProjectContract?: BridgeProjectContractResolvers<ContextType>;
  Brigade?: BrigadeResolvers<ContextType>;
  ConstructionProject?: ConstructionProjectResolvers<ContextType>;
  ConstructionSite?: ConstructionSiteResolvers<ContextType>;
  CustomerOrganization?: CustomerOrganizationResolvers<ContextType>;
  Date?: GraphQLScalarType;
  Employee?: EmployeeResolvers<ContextType>;
  EmployeeInfo?: EmployeeInfoResolvers<ContextType>;
  EngineerInfo?: EngineerInfoResolvers<ContextType>;
  EngineerPosition?: EngineerPositionResolvers<ContextType>;
  Manufacturer?: ManufacturerResolvers<ContextType>;
  Material?: MaterialResolvers<ContextType>;
  MaterialUsage?: MaterialUsageResolvers<ContextType>;
  Mutation?: MutationResolvers<ContextType>;
  ObjectType?: ObjectTypeResolvers<ContextType>;
  ProjectContract?: ProjectContractResolvers<ContextType>;
  ProjectType?: ProjectTypeResolvers<ContextType>;
  Query?: QueryResolvers<ContextType>;
  SchoolProject?: SchoolProjectResolvers<ContextType>;
  SchoolProjectContract?: SchoolProjectContractResolvers<ContextType>;
  WorkScheduleUnit?: WorkScheduleUnitResolvers<ContextType>;
  WorkType?: WorkTypeResolvers<ContextType>;
  WorkerInfo?: WorkerInfoResolvers<ContextType>;
  WorkerPosition?: WorkerPositionResolvers<ContextType>;
};

