import { CheckIcon } from 'lucide-react'
import { Link } from 'react-router'

import heroIllustration from '@/assets/momWithChild.svg'
import { Button } from '@/components/ui/button'
import { Card, CardContent } from '@/components/ui/card'

const previewSteps = [true, true, true, false, false]

const steps = [
  {
    title: 'Посмотрите пример',
    description: 'У каждого вопроса есть короткое видео с примером.',
  },
  {
    title: 'Ответьте без спешки',
    description: 'Ответы сохраняются: можно сделать перерыв.',
  },
  {
    title: 'Получите результат',
    description: 'Матрица и профиль останутся в вашем аккаунте',
  },
  {
    title: 'Поделитесь с врачом',
    description: 'Скачайте PDF или отправьте результат логоцентру.',
  },
]

export function HomePage() {
  return (
    <>
      <section className="page-container flex flex-col gap-8 pt-8 lg:flex-row lg:items-center lg:justify-between lg:pt-16">
        <div className="flex flex-col items-start gap-6">
          <span className="rounded-lg bg-secondary px-2 py-2 text-overline text-link">
            Онлайн-инструмент для родителей
          </span>
          <h1 className="text-hero text-foreground">Вместе к большему пониманию</h1>
          <p className="max-w-105 text-body text-muted-foreground">
            Замечайте маленькие шаги в развитии ребёнка. Матрица коммуникаций и сенсорный профиль —
            в одном спокойном пространстве.
          </p>
          <Button
            className="w-full lg:w-59"
            nativeButton={false}
            render={<Link to="/tests/matrix" />}
          >
            Начать тестирование
          </Button>
          <p className="text-small text-muted-foreground">
            Сохраните результаты и обсудите их со специалистом.
          </p>
        </div>

        <div className="relative flex flex-col items-center gap-4 rounded-xl bg-secondary p-4 ring-1 ring-border lg:h-104 lg:w-147 lg:shrink-0 lg:flex-row lg:justify-end lg:py-2 lg:pr-8 lg:pl-0">
          <img src={heroIllustration} alt="" width={268} height={400} />

          <div className="flex w-full flex-col gap-3 rounded-xl bg-card p-5 ring-1 ring-border lg:absolute lg:bottom-22 lg:left-4 lg:w-64">
            <div className="flex flex-col gap-1">
              <p className="text-body font-medium text-foreground">Каждый шаг важен</p>
              <p className="text-small text-muted-foreground">
                Сохраняйте достижения и наблюдайте изменения.
              </p>
            </div>

            <div className="flex gap-2" aria-hidden="true">
              {previewSteps.map((done, index) => (
                <span
                  key={index}
                  className={
                    done
                      ? 'flex size-8 items-center justify-center rounded-lg bg-success/10 text-success'
                      : 'flex size-8 items-center justify-center rounded-lg bg-muted text-muted-foreground'
                  }
                >
                  {done ? (
                    <CheckIcon className="size-4" />
                  ) : (
                    <span className="size-1 rounded-full bg-current" />
                  )}
                </span>
              ))}
            </div>
          </div>
        </div>
      </section>

      <section className="page-container flex flex-col gap-6 py-10 lg:gap-8 lg:pt-16 lg:pb-16">
        <h2 className="text-h3 text-foreground">Понятный путь — от вопроса к результату</h2>
        <ol className="grid grid-cols-1 gap-3 lg:grid-cols-4 lg:gap-5">
          {steps.map((step, index) => (
            <li key={step.title}>
              <Card className="h-full [--card-spacing:--spacing(6)]">
                <CardContent className="flex flex-col gap-3">
                  <span className="flex h-8 w-fit items-center justify-center rounded-lg bg-secondary px-2 text-label text-link">
                    {String(index + 1).padStart(2, '0')}
                  </span>
                  <h3 className="text-body font-medium text-foreground">{step.title}</h3>
                  <p className="text-small text-muted-foreground">{step.description}</p>
                </CardContent>
              </Card>
            </li>
          ))}
        </ol>
      </section>
    </>
  )
}
