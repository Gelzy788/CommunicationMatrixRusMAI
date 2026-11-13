import { createBrowserRouter, createRoutesFromElements, Navigate, Route } from 'react-router'

import { AboutPage } from '@/pages/AboutPage'
import { DeleteAccountPage } from '@/pages/DeleteAccountPage'
import { ErrorPage } from '@/pages/ErrorPage'
import { HomePage } from '@/pages/HomePage'
import { LoginPage } from '@/pages/LoginPage'
import { MatrixResultPage } from '@/pages/MatrixResultPage'
import { MatrixResultsListPage } from '@/pages/MatrixResultsListPage'
import { MatrixTestPage } from '@/pages/MatrixTestPage'
import { NotFoundPage } from '@/pages/NotFoundPage'
import { PrivacyPage } from '@/pages/PrivacyPage'
import { ProfilePage } from '@/pages/ProfilePage'
import { RegisterPage } from '@/pages/RegisterPage'
import { ShareResultPage } from '@/pages/ShareResultPage'
import { TestsPage } from '@/pages/TestsPage'

import { AppLayout } from './layouts/AppLayout'
import { RequireAuth } from './layouts/RequireAuth'

export const router = createBrowserRouter(
  createRoutesFromElements(
    <Route element={<AppLayout />} errorElement={<ErrorPage />}>
      <Route index element={<HomePage />} />
      <Route path="about" element={<AboutPage />} />
      <Route path="login" element={<LoginPage />} />
      <Route path="register" element={<RegisterPage />} />
      <Route path="privacy" element={<PrivacyPage />} />

      <Route element={<RequireAuth />}>
        <Route path="profile" element={<ProfilePage />} />
        <Route path="tests" element={<TestsPage />} />
        <Route path="tests/matrix" element={<MatrixTestPage />} />
        <Route path="results" element={<Navigate to="/results/matrix" replace />} />
        <Route path="results/matrix" element={<MatrixResultsListPage />} />
        <Route path="results/matrix/:resultId" element={<MatrixResultPage />} />
        <Route path="results/matrix/:resultId/share" element={<ShareResultPage />} />
        <Route path="profile/delete" element={<DeleteAccountPage />} />
      </Route>

      <Route path="*" element={<NotFoundPage />} />
    </Route>,
  ),
)
