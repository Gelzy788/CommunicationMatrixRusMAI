import { Link } from 'react-router'

import { Button } from '@/components/ui/button'

export function NotFoundPage() {
  return (
    <section className="page-container flex min-h-[calc(100dvh-var(--header-height)-var(--footer-height))] flex-col items-center justify-center py-(--section-gap) text-center">
      <p className="text-8xl leading-none font-bold text-primary lg:text-[174px]">404</p>
      <h1 className="mt-8 text-h1 text-foreground lg:mt-12">Страница не найдена</h1>
      <p className="mt-4 text-body text-muted-foreground">
        Возможно, ссылка устарела или в адресе есть опечатка. Вернитесь на главную — мы поможем
        найти нужное.
      </p>
      <Button render={<Link to="/" />} className="mt-6 w-full lg:w-56">
        На главную
      </Button>
    </section>
  )
}
