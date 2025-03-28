import React from 'react';
import RoleGuard from '../components/RoleGuard.tsx';
import ConstructionSiteInfo from '../components/ConstructionSiteInfo';

const Dashboard: React.FC = () => {
  return (
    <RoleGuard requiredRoles={['admin']}>
      <div className="dashboard">
        <h1>Construction Site Dashboard</h1>
        <ConstructionSiteInfo/>
      </div>
    </RoleGuard>
  );
};

export default Dashboard;