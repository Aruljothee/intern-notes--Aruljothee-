
import { useEffect, useState } from 'react'
import './App.css'
import keycloak, { initializeKeycloak } from './keycloak'
import StudentDashboard from './StudentDashboard'
import AdminDashboard from './AdminDashboard'

function App() {
  const [authenticated, setAuthenticated] = useState(false)
  const [user, setUser] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  useEffect(() => {
    let active = true

    async function setupKeycloak() {
      try {
        // Initialize Keycloak only once.
        const isAuthenticated = await initializeKeycloak()

        if (!active) return

        if (isAuthenticated) {
          const profile = await keycloak.loadUserProfile()

          if (!active) return

          const roles = keycloak.tokenParsed?.realm_access?.roles || []

          const role = roles.includes('ADMIN')
            ? 'ADMIN'
            : roles.includes('STUDENT')
              ? 'STUDENT'
              : null

          if (!role) {
            setError(
              'Your Keycloak account does not have a STUDENT or ADMIN role. Ask the administrator to assign one.'
            )
            return
          }

          setUser({
            email:
              profile.email ||
              keycloak.tokenParsed?.email ||
              keycloak.tokenParsed?.preferred_username ||
              '',
            role,
          })

          setAuthenticated(true)
        }
      } catch (err) {
        console.error('Keycloak initialization failed:', err)

        if (active) {
          setError(
            'Could not connect to Keycloak. Check that it is running at http://localhost:8180 and review the browser console.'
          )
        }
      } finally {
        if (active) {
          setLoading(false)
        }
      }
    }

    setupKeycloak()

    return () => {
      active = false
    }
  }, [])

  const signIn = async () => {
    try {
      await keycloak.login({
        redirectUri: window.location.origin,
      })
    } catch (err) {
      console.error('Keycloak login failed:', err)
      setError('Unable to start Keycloak login. Please try again.')
    }
  }

  const signOut = async () => {
    try {
      await keycloak.logout({
        redirectUri: window.location.origin,
      })
    } catch (err) {
      console.error('Keycloak logout failed:', err)
      setError('Unable to log out. Please try again.')
    }
  }

  if (loading) {
    return (
      <div className="app">
        <p>Connecting to secure login...</p>
      </div>
    )
  }

  if (error) {
    return (
      <div className="app">
        <header className="navbar">
          <div className="brand">
            <span className="brand-icon">CL</span>
            <span>CampusLeave</span>
          </div>
          <span className="nav-label">Leave Management System</span>
        </header>

        <main className="hero">
          <section className="login-card">
            <div className="card-symbol">CL</div>
            <h2>CampusLeave Login</h2>
            <p>{error}</p>

            <button
              className="sign-in"
              type="button"
              onClick={signIn}
            >
              Try Keycloak login
            </button>

            <p className="security-note">Secure college access</p>
          </section>
        </main>
      </div>
    )
  }

  if (authenticated && user?.role === 'STUDENT') {
    return (
      <StudentDashboard
        email={user.email}
        onLogout={signOut}
      />
    )
  }

  if (authenticated && user?.role === 'ADMIN') {
    return (
      <AdminDashboard
        email={user.email}
        onLogout={signOut}
      />
    )
  }

  return (
    <div className="app">
      <header className="navbar">
        <div className="brand">
          <span className="brand-icon">CL</span>
          <span>CampusLeave</span>
        </div>

        <span className="nav-label">
          Leave Management System
        </span>
      </header>

      <main className="hero">
        <section className="intro">
          <span className="eyebrow">COLLEGE LEAVE PORTAL</span>

          <h1>
            Manage your leave,
            <br />
            the simple way.
          </h1>

          <p>
            Apply for leave, track your requests, and stay updated
            throughout the approval process.
          </p>

          <div className="features">
            <div className="feature">
              <span className="feature-icon">01</span>
              <div>
                <h3>Easy applications</h3>
                <p>Submit leave requests in a few simple steps.</p>
              </div>
            </div>

            <div className="feature">
              <span className="feature-icon">02</span>
              <div>
                <h3>Track your status</h3>
                <p>See whether your request is pending or approved.</p>
              </div>
            </div>

            <div className="feature">
              <span className="feature-icon">03</span>
              <div>
                <h3>Clear decisions</h3>
                <p>
                  Administrators can review requests efficiently.
                </p>
              </div>
            </div>
          </div>
        </section>

        <section className="login-card">
          <div className="card-symbol">CL</div>

          <h2>Welcome to CampusLeave</h2>

          <p className="card-description">
            Sign in securely using Keycloak.
          </p>

          <button
            className="sign-in"
            type="button"
            onClick={signIn}
          >
            Sign in with Keycloak
          </button>

          <p className="security-note">
            Secure college access
          </p>
        </section>
      </main>

      <footer>
        © 2026 CampusLeave · College Leave Management System
      </footer>
    </div>
  )
}

export default App
