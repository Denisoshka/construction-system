import React from 'react';
import {gql, useQuery} from '@apollo/client';
import {useAuth} from '../auth/AuthProvider.tsx';

const GET_CONSTRUCTION_SITES_BY_MANAGER = gql`
  query GetConstructionSitesByManager($id: ID!) {
    constructionSitesBySiteManager(id: $id) {
      id
      name
      address
      phone
      siteManager {
        employee {
          id
          name
          surname
          patronymic
        }
        positionInfo {
          id
          name
        }
      }
    }
  }
`;

const ConstructionSiteInfo: React.FC = () => {
  const {userInfo} = useAuth();

  const {loading, error, data} = useQuery(
    GET_CONSTRUCTION_SITES_BY_MANAGER,
    {
      variables: {id: userInfo?.id},
      skip: !userInfo?.id
    }
  );

  if (loading) return <div className="loading">
    Loading construction site information...</div>;
  if (error) return <div className="error">Error: {error.message}</div>;
  if (!data?.constructionSiteBySiteManager) return <div
    className="no-data">No construction site assigned</div>;

  const site = data.constructionSiteBySiteManager;
  const manager = site.siteManager;

  return (
    <div className="construction-site-card">
      <div className="site-section">
        <h3>Construction Site Details</h3>
        <div className="detail-row">
          <span className="detail-label">Name:</span>
          <span className="detail-value">{site.name}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Address:</span>
          <span className="detail-value">{site.address}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Phone:</span>
          <span className="detail-value">{site.phone}</span>
        </div>
      </div>

      <div className="manager-section">
        <h3>My Manager Profile</h3>
        <div className="detail-row">
          <span className="detail-label">Full Name:</span>
          <span className="detail-value">
            {manager.employee.surname} {manager.employee.name} {manager.employee.patronymic}
          </span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Position:</span>
          <span className="detail-value">{manager.positionInfo.name}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Post:</span>
          <span className="detail-value">{manager.employee.post.name}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">Employment Date:</span>
          <span className="detail-value">
            {new Date(manager.employee.employmentDate).toLocaleDateString()}
          </span>
        </div>
      </div>
    </div>
  );
};

export default ConstructionSiteInfo;