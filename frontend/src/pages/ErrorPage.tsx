import { isRouteErrorResponse, Link, useNavigate, useRouteError } from 'react-router'

import { Button } from '@/components/ui/button'

type ErrorInfo = {
  code: string
  title: string
  description: string
  details?: string
}

function getErrorInfo(error: unknown, isDev: boolean): ErrorInfo {
  if (isRouteErrorResponse(error)) {
    return {
      code: String(error.status),
      title: error.statusText || 'Что-то пошло не так',
      description: 'Не удалось загрузить страницу. Попробуйте вернуться назад или на главную.',
      details: isDev && typeof error.data === 'string' ? error.data : undefined,
    }
  }

  return {
    code: 'Ой!',
    title: 'Что-то пошло не так',
    description:
      'На странице произошла ошибка. Мы уже знаем о проблеме — попробуйте вернуться назад или на главную.',
    details: isDev ? getDevDetails(error) : undefined,
  }
}

function getDevDetails(error: unknown): string {
  if (error instanceof Error) {
    return `${error.name}: ${error.message}\n\n${error.stack ?? ''}`
  }
  return String(error)
}

export function ErrorPage() {
  const navigate = useNavigate()
  const error = useRouteError()
  const { code, title, description, details } = getErrorInfo(error, import.meta.env.DEV)

  return (
    <section className="page-container flex min-h-dvh flex-col items-center justify-center py-(--section-gap) text-center">
      <p className="text-8xl leading-none font-bold text-primary lg:text-[174px]">{code}</p>
      <h1 className="mt-8 text-h1 text-foreground lg:mt-12">{title}</h1>
      <p className="mt-4 text-body text-muted-foreground">{description}</p>

      {details && (
        <pre className="mt-6 max-h-80 w-full overflow-auto rounded-xl border border-border bg-card p-4 text-left text-caption whitespace-pre-wrap text-destructive">
          {details}
        </pre>
      )}

      <div className="mt-6 flex w-full flex-col gap-3 lg:w-auto lg:flex-row">
        <Button variant="outline" onClick={() => void navigate(-1)} className="w-full lg:w-56">
          Назад
        </Button>
        <Button render={<Link to="/" />} className="w-full lg:w-56">
          На главную
        </Button>
      </div>
    </section>
  )
}
