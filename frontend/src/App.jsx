import { useState } from 'react'

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080';

export default function App() {
  const [longUrl, setLongUrl] = useState('')
  const [customLength, setCustomLength] = useState(7)
  const [result, setResult] = useState(null)
  const [error, setError] = useState(null)

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError(null)
    setResult(null)
    try {
      const res = await fetch(`${API_BASE}/api/shorten`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ longUrl, customLength: Number(customLength) })
      })
      if (!res.ok) {
        const text = await res.text()
        throw new Error(text || 'Failed to shorten')
      }
      const data = await res.json()
      setResult(data)
    } catch (err) {
      setError(err.message)
    }
  }

  return (
    <div style={{ maxWidth: 720, margin: '3rem auto', fontFamily: 'system-ui, sans-serif' }}>
      <h1>🔗 URL Shortener</h1>
      <p>Paste a long URL and get a short one. Backend at <code>{API_BASE}</code></p>

      <form onSubmit={handleSubmit} style={{ display: 'grid', gap: '0.75rem' }}>
        <input
          type="url"
          value={longUrl}
          onChange={(e) => setLongUrl(e.target.value)}
          placeholder="https://example.com/very/long/url"
          required
          style={{ padding: '0.6rem', fontSize: '1rem' }}
        />
        <label>
          Code length:&nbsp;
          <input
            type="number"
            min="4"
            max="12"
            value={customLength}
            onChange={(e) => setCustomLength(e.target.value)}
            style={{ width: 80, padding: '0.4rem' }}
          />
        </label>
        <button type="submit" style={{ padding: '0.7rem 1rem', fontSize: '1rem', cursor: 'pointer' }}>
          Shorten
        </button>
      </form>

      {error && <p style={{ color: 'red' }}>Error: {error}</p>}
      {result && (
        <div style={{ marginTop: '1rem', padding: '1rem', border: '1px solid #ddd', borderRadius: 8 }}>
          <p><strong>Short URL:</strong> <a href={result.shortUrl} target="_blank" rel="noreferrer">{result.shortUrl}</a></p>
          <p><strong>Original:</strong> {result.longUrl}</p>
          <p><strong>Code:</strong> <code>{result.code}</code></p>
        </div>
      )}
    </div>
  )
}
