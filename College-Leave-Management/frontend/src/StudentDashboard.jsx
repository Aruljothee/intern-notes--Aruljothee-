
import { useState } from 'react'
import './StudentDashboard.css'
import keycloak from './keycloak'

const API_URL = '/api/leaves';

function StudentDashboard({ email, onLogout }) {
  const [activeSection, setActiveSection] = useState('dashboard')
  const [leaveType, setLeaveType] = useState('Casual Leave')
  const [startDate, setStartDate] = useState('')
  const [endDate, setEndDate] = useState('')
  const [reason, setReason] = useState('')
  const [message, setMessage] = useState('')
  const [messageType, setMessageType] = useState('')
  const [requests, setRequests] = useState([])
  const [loading, setLoading] = useState(false)
  const [loadingRequests, setLoadingRequests] = useState(false)

  // Attach the Keycloak access token to authenticated API requests.
  async function authorizedFetch(url, options = {}) {
    if (!keycloak.authenticated || !keycloak.token) {
      throw new Error('Your session has expired. Please log in again.')
    }

    try {
      await keycloak.updateToken(30)
    } catch {
      throw new Error('Unable to refresh your session. Please log in again.')
    }

    return fetch(url, {
      ...options,
      headers: {
        ...options.headers,
        Authorization: `Bearer ${keycloak.token}`,
      },
    })
  }

  async function readResponse(response) {
    const data = await response.json().catch(() => ({}))

    if (!response.ok) {
      if (response.status === 401) {
        throw new Error('Session expired or token invalid. Please log in again.')
      }

      if (response.status === 403) {
        throw new Error('You do not have permission to perform this action.')
      }

      throw new Error(
        data.error || data.message || 'The request could not be completed.'
      )
    }

    return data
  }

  const cards = [
    {
      title: 'Apply for Leave',
      description: 'Submit a new leave request.',
      button: 'Apply Now',
      section: 'apply',
      icon: '📝',
    },
    {
      title: 'My Leave Requests',
      description: 'View your submitted leave applications.',
      button: 'View Requests',
      section: 'requests',
      icon: '📋',
    },
    {
      title: 'Leave Status',
      description: 'Check pending, approved, or rejected requests.',
      button: 'Check Status',
      section: 'status',
      icon: '📊',
    },
  ]

  async function loadRequests() {
    setLoadingRequests(true)
    setMessage('')
    setMessageType('')

    try {
      const response = await authorizedFetch(
        `${API_URL}?email=${encodeURIComponent(email)}`
      )

      const data = await readResponse(response)
      setRequests(Array.isArray(data) ? data : [])
    } catch (error) {
      setMessage(error.message || 'Cannot connect to the backend.')
      setMessageType('error')
    } finally {
      setLoadingRequests(false)
    }
  }

  async function handleSectionChange(section) {
    setActiveSection(section)
    setMessage('')
    setMessageType('')

    if (section === 'requests' || section === 'status') {
      await loadRequests()
    }
  }

  async function handleSubmit(event) {
    event.preventDefault()
    setMessage('')
    setMessageType('')

    if (!startDate || !endDate) {
      setMessage('Please select both start and end dates.')
      setMessageType('error')
      return
    }

    if (endDate < startDate) {
      setMessage('End date cannot be before the start date.')
      setMessageType('error')
      return
    }

    if (!reason.trim()) {
      setMessage('Please enter the reason for your leave.')
      setMessageType('error')
      return
    }

    setLoading(true)

    try {
      const response = await authorizedFetch(API_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email,
          leaveType,
          startDate,
          endDate,
          reason: reason.trim(),
        }),
      })

      const data = await readResponse(response)

      setMessage(data.message || 'Leave request submitted successfully!')
      setMessageType('success')

      setLeaveType('Casual Leave')
      setStartDate('')
      setEndDate('')
      setReason('')
    } catch (error) {
      setMessage(
        error.message ||
          'Cannot connect to the backend. Make sure Spring Boot is running.'
      )
      setMessageType('error')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="student-dashboard">
      <header className="dashboard-navbar">
        <div className="brand">
          <span className="brand-icon">CL</span>
          <span>CampusLeave</span>
        </div>

        <button
          className="logout-button"
          type="button"
          onClick={onLogout}
        >
          Logout
        </button>
      </header>

      <main className="dashboard-content">
        {activeSection === 'dashboard' && (
          <>
            <div className="dashboard-welcome">
              <span className="dashboard-eyebrow">STUDENT PORTAL</span>
              <h1>Student Dashboard</h1>
              <p className="welcome-text">Welcome, {email}!</p>
              <p>
                Manage your college leave requests from one place.
              </p>
            </div>

            <div className="dashboard-cards">
              {cards.map((card) => (
                <div className="dashboard-card" key={card.section}>
                  <span className="card-icon">{card.icon}</span>
                  <h2>{card.title}</h2>
                  <p>{card.description}</p>

                  <button
                    className="dashboard-action"
                    type="button"
                    onClick={() => handleSectionChange(card.section)}
                  >
                    {card.button}
                  </button>
                </div>
              ))}
            </div>
          </>
        )}

        {activeSection === 'apply' && (
          <section className="dashboard-panel">
            <button
              className="back-button"
              type="button"
              onClick={() => handleSectionChange('dashboard')}
            >
              ← Back to Dashboard
            </button>

            <h1>Apply for Leave</h1>
            <p>Fill in the details below to apply for leave.</p>

            <form className="leave-form" onSubmit={handleSubmit}>
              <label htmlFor="leaveType">Leave Type</label>
              <select
                id="leaveType"
                value={leaveType}
                onChange={(event) => setLeaveType(event.target.value)}
                required
              >
                <option value="Casual Leave">Casual Leave</option>
                <option value="Medical Leave">Medical Leave</option>
                <option value="Emergency Leave">Emergency Leave</option>
                <option value="Other">Other</option>
              </select>

              <label htmlFor="startDate">Start Date</label>
              <input
                id="startDate"
                type="date"
                value={startDate}
                min={new Date().toLocaleDateString('en-CA')}
                onChange={(event) => {
                  const selectedDate = event.target.value
                  setStartDate(selectedDate)

                  if (endDate && selectedDate > endDate) {
                    setEndDate('')
                  }
                }}
                required
              />

              <label htmlFor="endDate">End Date</label>
              <input
                id="endDate"
                type="date"
                value={endDate}
                min={startDate || new Date().toLocaleDateString('en-CA')}
                onChange={(event) => setEndDate(event.target.value)}
                required
              />

              <label htmlFor="reason">Reason for Leave</label>
              <textarea
                id="reason"
                value={reason}
                onChange={(event) => setReason(event.target.value)}
                placeholder="Explain why you need leave"
                rows="4"
                maxLength="500"
                required
              />

              <button
                className="dashboard-action"
                type="submit"
                disabled={loading}
              >
                {loading ? 'Submitting...' : 'Submit Leave Request'}
              </button>

              {message && (
                <p
                  role="status"
                  className={`leave-message ${messageType}`}
                >
                  {message}
                </p>
              )}
            </form>

            <p>Signed in as: {email}</p>
          </section>
        )}

        {activeSection === 'requests' && (
          <section className="dashboard-panel">
            <button
              className="back-button"
              type="button"
              onClick={() => handleSectionChange('dashboard')}
            >
              ← Back to Dashboard
            </button>

            <h1>My Leave Requests</h1>

            {loadingRequests ? (
              <p>Loading your leave requests...</p>
            ) : requests.length === 0 ? (
              <p>
                {message || 'You have not submitted any leave requests yet.'}
              </p>
            ) : (
              <div className="leave-request-list">
                {requests.map((request) => (
                  <article className="leave-request-item" key={request.id}>
                    <h2>{request.leaveType}</h2>
                    <p>
                      <strong>From:</strong> {request.startDate}
                    </p>
                    <p>
                      <strong>To:</strong> {request.endDate}
                    </p>
                    <p>
                      <strong>Reason:</strong> {request.reason}
                    </p>
                    <p>
                      <strong>Status:</strong> {request.status}
                    </p>
                  </article>
                ))}
              </div>
            )}

            {message && !loadingRequests && requests.length > 0 && (
              <p
                role="status"
                className={`leave-message ${messageType}`}
              >
                {message}
              </p>
            )}
          </section>
        )}

        {activeSection === 'status' && (
          <section className="dashboard-panel">
            <button
              className="back-button"
              type="button"
              onClick={() => handleSectionChange('dashboard')}
            >
              ← Back to Dashboard
            </button>

            <h1>Leave Status</h1>

            {loadingRequests ? (
              <p>Loading leave statuses...</p>
            ) : requests.length === 0 ? (
              <p>{message || 'No leave requests to display yet.'}</p>
            ) : (
              requests.map((request) => (
                <article className="leave-request-item" key={request.id}>
                  <h2>{request.leaveType}</h2>
                  <p>
                    {request.startDate} to {request.endDate}
                  </p>
                  <p>
                    <strong>Status:</strong> {request.status}
                  </p>
                </article>
              ))
            )}

            {message && !loadingRequests && requests.length > 0 && (
              <p
                role="status"
                className={`leave-message ${messageType}`}
              >
                {message}
              </p>
            )}
          </section>
        )}
      </main>

      <footer className="dashboard-footer">
        © 2026 CampusLeave · College Leave Management System
      </footer>
    </div>
  )
}

export default StudentDashboard
