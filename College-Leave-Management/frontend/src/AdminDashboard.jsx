
import { useCallback, useEffect, useState } from 'react'
import './AdminDashboard.css'
import keycloak from './keycloak'

const API_URL = '/api/leaves';

// Send the Keycloak access token with every protected API request.
async function authorizedFetch(url, options = {}) {
  if (!keycloak.authenticated || !keycloak.token) {
    throw new Error('Your session has expired. Please log in again.')
  }

  const refreshed = await keycloak.updateToken(30)

  if (!keycloak.token) {
    throw new Error('No access token is available. Please log in again.')
  }

  return fetch(url, {
    ...options,
    headers: {
      ...options.headers,
      Authorization: `Bearer ${keycloak.token}`,
    },
  })
}

function AdminDashboard({ email, onLogout }) {
  const [requests, setRequests] = useState([])
  const [loading, setLoading] = useState(true)
  const [message, setMessage] = useState('')
  const [updatingId, setUpdatingId] = useState(null)

  const fetchRequests = useCallback(async () => {
    setLoading(true)
    setMessage('')

    try {
      const response = await authorizedFetch(`${API_URL}/admin`)

      if (!response.ok) {
        if (response.status === 401) {
          throw new Error(
            'Unauthorized. Please log in again through Keycloak.'
          )
        }

        if (response.status === 403) {
          throw new Error(
            'Access denied. Your Keycloak user must have the ADMIN role.'
          )
        }

        throw new Error(
          `Unable to load leave requests (HTTP ${response.status}).`
        )
      }

      const data = await response.json()
      setRequests(data)
    } catch (error) {
      setMessage(error.message || 'Cannot connect to the backend.')
    } finally {
      setLoading(false)
    }
  }, [])

  useEffect(() => {
    fetchRequests()
  }, [fetchRequests])

  async function updateStatus(id, status) {
    setUpdatingId(id)
    setMessage('')

    try {
      const response = await authorizedFetch(`${API_URL}/${id}/status`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ status }),
      })

      if (!response.ok) {
        if (response.status === 401) {
          throw new Error('Unauthorized. Please log in again.')
        }

        if (response.status === 403) {
          throw new Error(
            'Access denied. Your Keycloak user must have the ADMIN role.'
          )
        }

        throw new Error(
          `Unable to update leave status (HTTP ${response.status}).`
        )
      }

      const data = await response.json()

      setRequests((previous) =>
        previous.map((request) =>
          request.id === id
            ? { ...request, status: data.status }
            : request
        )
      )

      setMessage(
        `Leave request ${status.toLowerCase()} successfully.`
      )
    } catch (error) {
      setMessage(error.message || 'Cannot connect to the backend.')
    } finally {
      setUpdatingId(null)
    }
  }

  const pendingCount = requests.filter(
    (request) => request.status === 'PENDING'
  ).length

  const approvedCount = requests.filter(
    (request) => request.status === 'APPROVED'
  ).length

  const rejectedCount = requests.filter(
    (request) => request.status === 'REJECTED'
  ).length

  return (
    <div className="admin-dashboard">
      <header className="admin-navbar">
        <div className="admin-brand">
          <span className="admin-brand-icon">CL</span>
          <span>CampusLeave</span>
          <span className="admin-label">ADMIN</span>
        </div>

        <button
          className="admin-logout"
          type="button"
          onClick={onLogout}
        >
          Logout
        </button>
      </header>

      <main className="admin-content">
        <section className="admin-welcome">
          <span className="admin-eyebrow">
            ADMINISTRATION PORTAL
          </span>

          <h1>Admin Dashboard</h1>

          <p>
            Review student leave applications and update their status.
          </p>

          <p className="admin-email">
            Signed in as: {email || keycloak.tokenParsed?.email || keycloak.tokenParsed?.preferred_username || 'Admin'}
          </p>
        </section>

        <section className="admin-statistics">
          <article className="admin-stat-card">
            <span>Total Requests</span>
            <strong>{requests.length}</strong>
          </article>

          <article className="admin-stat-card">
            <span>Pending</span>
            <strong>{pendingCount}</strong>
          </article>

          <article className="admin-stat-card">
            <span>Approved</span>
            <strong>{approvedCount}</strong>
          </article>

          <article className="admin-stat-card">
            <span>Rejected</span>
            <strong>{rejectedCount}</strong>
          </article>
        </section>

        <section className="admin-requests-panel">
          <div className="admin-panel-heading">
            <div>
              <h2>Student Leave Requests</h2>
              <p>
                Review applications submitted by students.
              </p>
            </div>

            <button
              className="admin-refresh"
              type="button"
              onClick={fetchRequests}
              disabled={loading || updatingId !== null}
            >
              {loading ? 'Loading...' : 'Refresh'}
            </button>
          </div>

          {message && (
            <p className="admin-message" role="status">
              {message}
            </p>
          )}

          {loading ? (
            <p className="admin-empty">
              Loading leave requests...
            </p>
          ) : requests.length === 0 ? (
            <p className="admin-empty">
              No leave requests found, or the requests could not be loaded.
            </p>
          ) : (
            <div className="admin-request-list">
              {requests.map((request) => (
                <article
                  className="admin-request-card"
                  key={request.id}
                >
                  <div className="admin-request-heading">
                    <div>
                      <h3>{request.leaveType}</h3>

                      <p className="admin-student-email">
                        {request.studentEmail}
                      </p>
                    </div>

                    <span
                      className={`admin-status ${(
                        request.status || 'PENDING'
                      ).toLowerCase()}`}
                    >
                      {request.status}
                    </span>
                  </div>

                  <div className="admin-request-details">
                    <p>
                      <strong>Request ID:</strong> {request.id}
                    </p>

                    <p>
                      <strong>Start date:</strong> {request.startDate}
                    </p>

                    <p>
                      <strong>End date:</strong> {request.endDate}
                    </p>

                    <p>
                      <strong>Reason:</strong> {request.reason}
                    </p>
                  </div>

                  {request.status === 'PENDING' && (
                    <div className="admin-request-actions">
                      <button
                        className="approve-button"
                        type="button"
                        disabled={updatingId !== null}
                        onClick={() =>
                          updateStatus(request.id, 'APPROVED')
                        }
                      >
                        {updatingId === request.id
                          ? 'Updating...'
                          : 'Approve'}
                      </button>

                      <button
                        className="reject-button"
                        type="button"
                        disabled={updatingId !== null}
                        onClick={() =>
                          updateStatus(request.id, 'REJECTED')
                        }
                      >
                        {updatingId === request.id
                          ? 'Updating...'
                          : 'Reject'}
                      </button>
                    </div>
                  )}
                </article>
              ))}
            </div>
          )}
        </section>
      </main>

      <footer className="admin-footer">
        © 2026 CampusLeave · College Leave Management System
      </footer>
    </div>
  )
}

export default AdminDashboard
