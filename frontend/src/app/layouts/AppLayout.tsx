import { Outlet } from 'react-router'

import { Footer } from './Footer'
import { Header } from './Header'

export function AppLayout() {
  return (
    // Footer прижат к низу даже на пустой странице
    <div className="flex min-h-dvh flex-col">
      <Header />
      <main className="flex-1">
        <Outlet />
      </main>
      <Footer />
    </div>
  )
}
